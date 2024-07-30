import { TasksTable } from "@/_components/tasks-table";
import { TasksTableProvider } from "@/_components/tasks-table-provider";
import { DataTableSkeleton } from "@/components/data-table/data-table-skeleton";
import { DateRangePicker } from "@/components/DataRangePicker";
import { Shell } from "@/components/Shell";
import { Skeleton } from "@/components/ui/skeleton";
import { getRepositoryStatistics } from "@/lib/gitparse/api";
import { SearchParamsSchema } from "@/lib/validation";
import { SearchParams } from "@/types";
import { TooltipProvider } from "@radix-ui/react-tooltip";
import React from "react";
import { useParams, useSearchParams } from "react-router-dom";

export interface IndexPageProps {
    searchParams: SearchParams;
}

const RepositoryStatistics = () => {
    const { owner, repo } = useParams<{
        owner: string;
        repo: string;
    }>();

    const [searchParams] = useSearchParams();

    const search = SearchParamsSchema.parse({
        owner: owner,
        repo: repo,
        // page: searchParams.get("page"),
        // per_page: searchParams.get("per_page"),
        // sort: searchParams.get("sort"),
        // commiter: searchParams.get("commiter"),
        // branch: searchParams.get("branch"),
        // from: searchParams.get("from"),
        // to: searchParams.get("to"),
        // operator: searchParams.get("operator"),
    });

    const statsPromise = getRepositoryStatistics(search); // TODO: implement getTasks

    return (
        <TooltipProvider>
            <Shell className="gap-2">
                <TasksTableProvider>
                    <React.Suspense
                        fallback={<Skeleton className="h-7 w-52" />}>
                        <DateRangePicker
                            triggerSize="sm"
                            triggerClassName="ml-auto w-56 sm:w-60"
                            align="end"
                        />
                    </React.Suspense>
                    <React.Suspense
                        fallback={
                            <DataTableSkeleton
                                columnCount={5}
                                searchableColumnCount={1}
                                filterableColumnCount={2}
                                cellWidths={[
                                    "10rem",
                                    "40rem",
                                    "12rem",
                                    "12rem",
                                    "8rem",
                                ]}
                                shrinkZero
                            />
                        }>
                        <TasksTable tasksPromise={statsPromise} />
                    </React.Suspense>
                </TasksTableProvider>
            </Shell>
        </TooltipProvider>
    );
};

export default RepositoryStatistics;
