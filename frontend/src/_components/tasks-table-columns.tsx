"use client";

// import * as React from "react";
// import { tasks, type Task } from "@/db/schema";
// import { DotsHorizontalIcon } from "@radix-ui/react-icons";
import { type ColumnDef } from "@tanstack/react-table";
// import { toast } from "sonner";

// import { getErrorMessage } from "@/lib/handle-error";
// import { formatDate } from "@/lib/utils";
import { Badge } from "@/components/ui/badge";
// import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
// import {
//     DropdownMenu,
//     DropdownMenuContent,
//     DropdownMenuItem,
//     DropdownMenuRadioGroup,
//     DropdownMenuRadioItem,
//     DropdownMenuSeparator,
//     DropdownMenuShortcut,
//     DropdownMenuSub,
//     DropdownMenuSubContent,
//     DropdownMenuSubTrigger,
//     DropdownMenuTrigger,
// } from "@/components/ui/dropdown-menu";
import { DataTableColumnHeader } from "@/components/data-table/data-table-column-header";

// import { updateTask } from "../_lib/actions"
// import { getPriorityIcon, getStatusIcon } from "../_lib/utils"
// import { DeleteTasksDialog } from "./delete-tasks-dialog";
// import { UpdateTaskSheet } from "./update-task-sheet";
import { Statistics } from "@/types";
import { formatDate } from "@/lib/utils";

export function getColumns(): ColumnDef<Statistics>[] {
    return [
        {
            id: "select",
            header: ({ table }) => (
                <Checkbox
                    checked={
                        table.getIsAllPageRowsSelected() ||
                        (table.getIsSomePageRowsSelected() && "indeterminate")
                    }
                    onCheckedChange={(value) =>
                        table.toggleAllPageRowsSelected(!!value)
                    }
                    aria-label="Select all"
                    className="translate-y-0.5"
                />
            ),
            cell: ({ row }) => (
                <Checkbox
                    checked={row.getIsSelected()}
                    onCheckedChange={(value) => row.toggleSelected(!!value)}
                    aria-label="Select row"
                    className="translate-y-0.5"
                />
            ),
            enableSorting: false,
            enableHiding: false,
        },
        {
            accessorKey: "sha",
            header: ({ column }) => (
                <DataTableColumnHeader column={column} title="Sha" />
            ),
            cell: ({ row }) => {
                console.log("sha", row.original.sha);
                if (!row.original.sha) return "N/A";

                const sha = row.original.sha.slice(0, 7);
                return (
                    <a href={row.original.url} target="_blank" className="w-20">
                        {sha}
                    </a>
                );
            },
            enableSorting: false,
            enableHiding: false,
        },
        {
            accessorKey: "message",
            header: ({ column }) => (
                <DataTableColumnHeader column={column} title="Message" />
            ),
            cell: ({ row }) => {
                if (!row.original.message) return "N/A";

                const [type, message] = row.original.message.split(":");

                return (
                    <div className="flex space-x-2 overflow-clip">
                        {type && <Badge variant="outline">{type}</Badge>}
                        <span className="max-w-[31.25rem] truncate font-medium">
                            {row.getValue("message")}
                        </span>
                    </div>
                );
            },
        },
        {
            accessorKey: "author",
            header: ({ column }) => (
                <DataTableColumnHeader column={column} title="Author" />
            ),
            cell: ({ row }) => {
                const author = row.original?.committer.name;

                if (!author) return null;

                return (
                    <div className="flex w-[6.25rem] items-center">
                        <span className="capitalize">{author}</span>
                    </div>
                );
            },
            filterFn: (row, id, value) => {
                return Array.isArray(value) && value.includes(row.getValue(id));
            },
        },
        {
            accessorKey: "branch",
            header: ({ column }) => (
                <DataTableColumnHeader column={column} title="Branch" />
            ),
            cell: ({ row }) => {
                const branch = row.original.branch?.name
                    ? row.original?.branch.name
                    : "N/A";

                if (!branch) return null;
                return (
                    <a
                        // href={row.original.branch.url} target="_blank"
                        className="flex w-[6.25rem] items-center">
                        <span className="capitalize">{branch}</span>
                    </a>
                );
            },
            filterFn: (row, id, value) => {
                return Array.isArray(value) && value.includes(row.getValue(id));
            },
        },
        {
            accessorKey: "changes",
            header: ({ column }) => (
                <DataTableColumnHeader column={column} title="Changes" />
            ),
            cell: ({ row }) => {
                const additions = row.original?.stats.additions;
                const deletions = row.original?.stats.deletions;

                if (!additions && !deletions) return null;
                return (
                    <div className="flex w-[8.25rem] items-center">
                        <span className="capitalize text-commitAdditionGreen">
                            {additions}&nbsp;++&nbsp;
                        </span>
                        <span className="mx-1">/</span>
                        <span className="capitalize text-commitDeletionRed">
                            &nbsp;{deletions}&nbsp;--
                        </span>
                    </div>
                );
            },
            filterFn: (row, id, value) => {
                return Array.isArray(value) && value.includes(row.getValue(id));
            },
        },
        {
            accessorKey: "createdAt",
            header: ({ column }) => (
                <DataTableColumnHeader column={column} title="Created At" />
            ),
            cell: ({ row }) => {
                // console.log("cell", row.);
                return formatDate(row.original.datetime);
            },
        },
        // {
        //     id: "actions",
        //     cell: function Cell({ row }) {
        //         const [isUpdatePending, startUpdateTransition] =
        //             React.useTransition();
        //         const [showUpdateTaskSheet, setShowUpdateTaskSheet] =
        //             React.useState(false);
        //         const [showDeleteTaskDialog, setShowDeleteTaskDialog] =
        //             React.useState(false);

        //         return (
        //             <>
        //                 <UpdateTaskSheet
        //                     open={showUpdateTaskSheet}
        //                     onOpenChange={setShowUpdateTaskSheet}
        //                     task={row.original}
        //                 />
        //                 <DeleteTasksDialog
        //                     open={showDeleteTaskDialog}
        //                     onOpenChange={setShowDeleteTaskDialog}
        //                     tasks={[row.original]}
        //                     showTrigger={false}
        //                     onSuccess={() => row.toggleSelected(false)}
        //                 />
        //                 <DropdownMenu>
        //                     <DropdownMenuTrigger asChild>
        //                         <Button
        //                             aria-label="Open menu"
        //                             variant="ghost"
        //                             className="flex size-8 p-0 data-[state=open]:bg-muted">
        //                             <DotsHorizontalIcon
        //                                 className="size-4"
        //                                 aria-hidden="true"
        //                             />
        //                         </Button>
        //                     </DropdownMenuTrigger>
        //                     <DropdownMenuContent align="end" className="w-40">
        //                         <DropdownMenuItem
        //                             onSelect={() =>
        //                                 setShowUpdateTaskSheet(true)
        //                             }>
        //                             Edit
        //                         </DropdownMenuItem>
        //                         <DropdownMenuSub>
        //                             <DropdownMenuSubTrigger>
        //                                 Labels
        //                             </DropdownMenuSubTrigger>
        //                             <DropdownMenuSubContent>
        //                                 <DropdownMenuRadioGroup
        //                                     value={row.original.label}
        //                                     onValueChange={(value) => {
        //                                         startUpdateTransition(() => {
        //                                             toast.promise(
        //                                                 updateTask({
        //                                                     id: row.original.id,
        //                                                     label: value as Task["label"],
        //                                                 }),
        //                                                 {
        //                                                     loading:
        //                                                         "Updating...",
        //                                                     success:
        //                                                         "Label updated",
        //                                                     error: (err) =>
        //                                                         getErrorMessage(
        //                                                             err
        //                                                         ),
        //                                                 }
        //                                             );
        //                                         });
        //                                     }}>
        //                                     {tasks.label.enumValues.map(
        //                                         (label) => (
        //                                             <DropdownMenuRadioItem
        //                                                 key={label}
        //                                                 value={label}
        //                                                 className="capitalize"
        //                                                 disabled={
        //                                                     isUpdatePending
        //                                                 }>
        //                                                 {label}
        //                                             </DropdownMenuRadioItem>
        //                                         )
        //                                     )}
        //                                 </DropdownMenuRadioGroup>
        //                             </DropdownMenuSubContent>
        //                         </DropdownMenuSub>
        //                         <DropdownMenuSeparator />
        //                         <DropdownMenuItem
        //                             onSelect={() =>
        //                                 setShowDeleteTaskDialog(true)
        //                             }>
        //                             Delete
        //                             <DropdownMenuShortcut>
        //                                 ⌘⌫
        //                             </DropdownMenuShortcut>
        //                         </DropdownMenuItem>
        //                     </DropdownMenuContent>
        //                 </DropdownMenu>
        //             </>
        //         );
        //     },
        // },
    ];
}
