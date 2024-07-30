import { useGetRepositoryByOwnerRepo } from "@/lib/react-query/queriesAndMutations";
import React from "react";
import { useParams } from "react-router-dom";

const RepositoryHeader = () => {
    const { owner, repo } = useParams<{ owner: string; repo: string }>();
    const { data: repository, isPending } = useGetRepositoryByOwnerRepo(
        owner,
        repo
    );

    return (
        <div className="flex-1 pt-5 px-5">
            <div className="h3-bold md:h2-bold text-left w-full flex gap-3 items-center">
                <img
                    src={
                        repository?.avatarUrl ||
                        "/assets/icons/profile-placeholder.svg"
                    }
                    alt="profile"
                    className="h-14 w-14 rounded-full"
                />
                <div className="flex flex-col">
                    <a
                        className="h2-bold hover:text-primary-500"
                        href={repository?.webUrl}
                        target="_blank">
                        {repository?.name}
                    </a>
                    <a
                        className="body-medium text-light-3"
                        // href={repository?.owner?.url}
                        target="_blank">
                        {repository?.ownerUsername}
                    </a>
                </div>
            </div>
        </div>
    );
};

export default RepositoryHeader;
