"use client";

import { DownloadIcon } from "@radix-ui/react-icons";
import { type Table } from "@tanstack/react-table";

import { exportTableToCSV } from "@/lib/export";
import { Button } from "@/components/ui/button";

import { Statistics } from "@/types";

interface TasksTableToolbarActionsProps {
    table: Table<Statistics>;
}

export function TasksTableToolbarActions({
    table,
}: TasksTableToolbarActionsProps) {
    return (
        <div className="flex items-center gap-2">
            {/* {table.getFilteredSelectedRowModel().rows.length > 0 ? (
                <DeleteTasksDialog
                    tasks={table
                        .getFilteredSelectedRowModel()
                        .rows.map((row) => row.original)}
                    onSuccess={() => table.toggleAllRowsSelected(false)}
                />
            ) : null}
            <CreateTaskDialog /> */}
            <Button
                variant="outline"
                size="sm"
                onClick={() =>
                    exportTableToCSV(table, {
                        filename: "tasks",
                        excludeColumns: ["select", "actions"],
                    })
                }>
                <DownloadIcon className="mr-2 size-4" aria-hidden="true" />
                Export
            </Button>
            {/**
             * Other actions can be added here.
             * For example, import, view, etc.
             */}
        </div>
    );
}
