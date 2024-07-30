"use client";

import * as React from "react";
// import { tasks, type Task } from "@/db/schema";
import type { DataTableFilterField } from "@/types";

import { useDataTable } from "@/hooks/use-data-table";
import { DataTableAdvancedToolbar } from "@/components/data-table/advanced/data-table-advanced-toolbar";
import { DataTable } from "@/components/data-table/data-table";
import { DataTableToolbar } from "@/components/data-table/data-table-toolbar";

// import type { getTasks } from "../_lib/queries";
import { getColumns } from "./tasks-table-columns";
import { TasksTableFloatingBar } from "./tasks-table-floating-bar";
import { useTasksTable } from "./tasks-table-provider";
import { TasksTableToolbarActions } from "./tasks-table-toolbar-actions";
import { getRepositoryStatistics } from "@/lib/gitparse/api";

type Statistics = any;

interface TasksTableProps {
    tasksPromise: ReturnType<typeof getRepositoryStatistics>;
}

export function TasksTable({ tasksPromise }: TasksTableProps) {
    //TODO: replace with StatsTableProps
    // Feature flags for showcasing some additional features. Feel free to remove them.
    const { featureFlags } = useTasksTable();

    const [data, setData] = React.useState<Statistics[]>([]); //Replace with Stats[]
    const [pageCount, setPageCount] = React.useState(0);

    React.useEffect(() => {
        const fetchData = async () => {
            try {
                const { data: fetchedData, pageCount: fetchedPageCount } =
                    await tasksPromise;
                setData(fetchedData);
                setPageCount(fetchedPageCount);
            } catch (error) {
                console.error("Error fetching statistics:", error);
            }
        };

        fetchData();

        // Cleanup function if needed (e.g., for aborting fetch)
        // return () => { /* cleanup code */ };
    }, [tasksPromise]); // Run effect whenever tasksPromise changes

    // Memoize the columns so they don't re-render on every render
    const columns = React.useMemo(() => getColumns(), []);

    const filterFields: DataTableFilterField<Statistics>[] = [
        // {
        //     label: "Branch",
        //     value: "branch.name",
        //     placeholder: "Filter branches...",
        // },
        // {
        //     label: "Author",
        //     value: "authorName",
        //     placeholder: "Filter authors...",
        // },
    ];

    const { table } = useDataTable({
        data,
        columns,
        pageCount,
        // optional props
        filterFields,
        enableAdvancedFilter: featureFlags.includes("advancedFilter"),
        defaultPerPage: 10,
        defaultSort: "createdAt.desc",
    });

    return (
        <DataTable
            table={table}
            floatingBar={
                featureFlags.includes("floatingBar") ? (
                    <TasksTableFloatingBar table={table} />
                ) : null
            }>
            {featureFlags.includes("advancedFilter") ? (
                <DataTableAdvancedToolbar
                    table={table}
                    filterFields={filterFields}>
                    <TasksTableToolbarActions table={table} />
                </DataTableAdvancedToolbar>
            ) : (
                <DataTableToolbar table={table} filterFields={filterFields}>
                    <TasksTableToolbarActions table={table} />
                </DataTableToolbar>
            )}
        </DataTable>
    );
}
