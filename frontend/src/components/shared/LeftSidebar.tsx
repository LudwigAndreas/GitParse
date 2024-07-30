import { Link, NavLink, useLocation, useNavigate } from "react-router-dom";

import { useUserContext } from "@/context/AuthContext";
import { useEffect } from "react";
import { useSignOutAccount } from "@/lib/react-query/queriesAndMutations";
import { INavLink } from "@/types";
import { sidebarLinks } from "@/constants/";
import { Separator } from "@/components/ui/separator";
import { useParams } from "react-router-dom";
import ListRepositories from "./ListRepositories";

const LeftSidebar = () => {
    const { mutate: signOut, isSuccess } = useSignOutAccount();
    const navigate = useNavigate();
    const { user } = useUserContext();
    const { pathname } = useLocation();

    useEffect(() => {
        if (isSuccess) {
            navigate(0);
        }
    }, [isSuccess]);

    // async function loadRepositories() {
    //     const { repositories } =
    // }

    return (
        <nav className="leftsidebar ">
            <Link to="/" className="flex gap-3 items-center pb-12">
                <img src="/assets/images/logo.svg" width={170} height={36} />
            </Link>
            <div className="w-[320px] min-h-[432px] flex-auto flex-col gap-11 overflow-y-auto custom-scrollbar">
                <Link
                    to={"/profile/${user.id}"}
                    className="flex gap-3 items-center pb-5 ">
                    <img
                        src={
                            user.imageUrl ||
                            "/assets/icons/profile-placeholder.svg"
                        }
                        alt="profile"
                        className="h-14 w-14 rounded-full"
                    />
                    <div className="flex flex-col">
                        <p className="body-bold">{user.name}</p>
                        <p className="small-regular text-light-3">
                            @{user.username}
                        </p>
                    </div>
                </Link>
                <ul className="flex flex-col gap-2 pr-2">
                    {sidebarLinks
                        .filter((link) => link.visible === "always")
                        .map((link: INavLink) => {
                            const isActive = pathname.includes(link.route);

                            return (
                                <li
                                    key={link.label}
                                    className={`leftsidebar-link group ${
                                        isActive &&
                                        "bg-primary-500 text-light-1 hover:bg-primary-500"
                                    }`}>
                                    <NavLink
                                        to={link.route}
                                        className="flex gap-4 items-center p-4">
                                        <img
                                            src={link.imgUrl}
                                            alt={link.label}
                                            className={`w-6 h-6 group-hover:invert-white ${
                                                isActive && "invert-white"
                                            }`}
                                        />
                                        {link.label}
                                    </NavLink>
                                </li>
                            );
                        })}

                    {sidebarLinks
                        .filter((link) => link.visible === "repository")
                        .map((link: INavLink) => {
                            const isActive = pathname.includes(link.route);
                            const isInRepo = pathname.includes("repository");
                            const { owner, repo } = useParams<{
                                owner: string;
                                repo: string;
                            }>();

                            return !isInRepo ? null : (
                                <li
                                    key={link.label}
                                    className={`leftsidebar-link group ${
                                        isActive &&
                                        "bg-primary-500 text-light-1 hover:bg-primary-500"
                                    }`}>
                                    <NavLink
                                        to={`/repository/${owner}/${repo}${link.route}`}
                                        className="flex gap-4 items-center p-4">
                                        <img
                                            src={link.imgUrl}
                                            alt={link.label}
                                            className={`w-6 h-6 group-hover:invert-white ${
                                                isActive && "invert-white"
                                            }`}
                                        />
                                        {link.label}
                                    </NavLink>
                                </li>
                            );
                        })}
                    <Separator className="my-1 bg-slate-500" />
                    <ListRepositories />
                </ul>
            </div>
        </nav>
    );
};

export default LeftSidebar;
