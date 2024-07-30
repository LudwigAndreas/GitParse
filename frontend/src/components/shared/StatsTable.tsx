import { getRepositoryStatistics } from "@/lib/gitparse/api";
import * as React from "react";
import { useStatsTable } from "../data-table/StatsTableProvider";

import { getColumns } from "../data-table/StatsTableColumns";
import { DataTableFilterField, Stats } from "@/types";
import { useDataTable } from "../hooks/UseDataTable";
import { DataTableToolbar } from "../data-table/DataTableToolbar";
import { DataTable } from "../data-table/DataTable";

interface StatsTableProps {
    statsPromise: ReturnType<typeof getRepositoryStatistics>;
}

export function StatsTable({ statsPromise }: StatsTableProps) {
    //
    const { featureFlags } = useStatsTable();

    const { data, pageCount } = React.use(statsPromise);

    // Запоминаем столбцы, чтобы они не отображались повторно при каждом рендеринге.
    const columns = React.useMemo(() => getColumns(), []);

    const filterFields: DataTableFilterField<Stats>[] = [
        {
            label: "Branch",
            value: "branch",
            placeholder: "Filter by branch",
        },
        {
            label: "User",
            value: "user",
            placeholder: "Filter by users",
        },
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
            // floatingBar={
            //     featureFlags.includes("floatingBar") ? (
            //         <StatsTableFloatingBar table={table} />
            //     ) : null
            // }
        >
            {
                // featureFlags.includes("advancedFilter") ? (
                //     <DataTableAdvancedToolbar
                //         table={table}
                //         filterFields={filterFields}>
                //         <StatsTableToolbarActions table={table} />
                //     </DataTableAdvancedToolbar>
                // ) : (
                <DataTableToolbar table={table} filterFields={filterFields}>
                    {/* <TasksTableToolbarActions table={table} /> */}
                </DataTableToolbar>
                // )
            }
        </DataTable>
    );
}

export default StatsTable;
