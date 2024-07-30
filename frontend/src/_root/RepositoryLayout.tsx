import RepositoryHeader from "@/components/shared/RepositoryHeader";
import React from "react";
import { Outlet } from "react-router-dom";

const RepositoryLayout = () => {
    return (
        <div className="w-full flex-col">
            <RepositoryHeader />

            <section className="flex flex-1">
                <Outlet />
            </section>
        </div>
    );
};

export default RepositoryLayout;
