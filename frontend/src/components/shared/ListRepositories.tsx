import React, { useEffect, useState } from "react";

import { useGetAllRepositories } from "@/lib/react-query/queriesAndMutations";
import { IRepository } from "@/types";
import { NavLink, useLocation } from "react-router-dom";
import { Button } from "../ui/button";

const ListRepositories = () => {
    const { pathname } = useLocation();

    const { mutateAsync: getAllRepositories, isPending: isFetching } =
        useGetAllRepositories();

    const [repositories, setRepositories] = useState<IRepository[]>([]);
    const [nextPage, setNextPage] = useState<number | null>(1);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const loadRepositories = async (page: number) => {
        setLoading(true);
        setError(null);
        try {
            const items = await getAllRepositories({
                page,
                perPage: 10,
            });
            setRepositories((prev) => [...prev, ...items]);
            setNextPage(nextPage);
        } catch (error) {
            setError("Failed to fetch repositories");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadRepositories(1);
    }, []);

    return (
        <>
            <p>Repositories</p>
            {repositories.map((repo) => {
                const isActive = pathname.includes(
                    `/repository/${repo.ownerUsername}/${repo.name}`
                );
                return (
                    <li
                        key={repo.id}
                        className={`leftsidebar-link group ${
                            isActive && "bg-primary-500 hover:bg-primary-500"
                        }`}>
                        <NavLink
                            to={`/repository/${repo.ownerUsername}/${repo.name}/user-chart`}
                            className="flex gap-2 items-center text-wrap pl-4 p-1">
                            <img
                                src={repo.avatarUrl}
                                alt={repo.ownerUsername}
                                className="h-10 w-10 rounded-full"
                            />
                            <div className="flex flex-col">
                                <p className="base-regular">{repo.name}</p>
                                <p className="small-regular text-light-3">
                                    {repo.ownerUsername}
                                </p>
                            </div>
                        </NavLink>
                    </li>
                );
            })}
            <Button
                variant="ghost"
                className="shad-button_ghost text-light-3"
                onClick={
                    nextPage ? () => loadRepositories(nextPage) : undefined
                }>
                {loading ? "Loading..." : "Load More"}
            </Button>
        </>
    );
};

export default ListRepositories;
