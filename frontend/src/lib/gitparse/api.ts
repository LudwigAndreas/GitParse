import { Statistics, INewUser, IRepository, PaginatedResponse } from "@/types";
import { gitparseConfig } from "./config";
import { mockRepos, mockStats, mockUser, mockUserSerssion } from "./mock";
import axios from "axios";
import { GetStatsSchema } from "../validation";

const api = axios.create({
    baseURL: gitparseConfig.url,
    headers: {
        "Content-Type": "application/json",
    },
    withCredentials: true,
});

export async function createUserAccount(user: INewUser) {
    // try {
    //     const response = await fetch(gitparseConfig.url + "/sign-up", {
    //         method: "POST",
    //         headers: {
    //             "Content-Type": "application/json",
    //         },
    //         body: JSON.stringify(user),
    //     });

    //     if (response.ok) {
    //         const data = await response.json();
    //         return data;
    //     } else {
    //         throw new Error("Failed to create user");
    //     }
    // } catch (error) {
    //     console.error(error);
    //     return error;
    // }
    return mockUser;
}

export async function signInAccount(user: { email: string; password: string }) {
    // try {
    //     const data = await fetch(gitparseConfig.url + "/sign-in", {
    //         method: "POST",
    //         headers: {
    //             "Content-Type": "application/json",
    //         },
    //         body: JSON.stringify(user),
    //     });
    //     if (data.ok) {
    //         const session = await data.json();
    //         return session;
    //     } else {
    //         throw new Error("Failed to sign in");
    //     }
    // } catch (error) {
    //     console.error(error);
    //     return error;
    // }
    // return mockUserSerssion;
    return (window.location.href = `${gitparseConfig.url}/oauth2/authorization/github`);
}

export async function getCurrentUser() {
    // try {
    //     const data = await fetch(gitparseConfig.url + "/me", {});

    //     if (!data.ok) {
    //         throw new Error("Failed to get current user");
    //     }

    //     const currentUser = await data.json();

    //     return currentUser;
    // } catch (error) {
    //     console.error(error);
    //     return error;
    // }
    return mockUser;
}

export async function signOutAccount() {
    // try {
    //     const data = await fetch(gitparseConfig.url + "/sign-out", {
    //         method: "POST",
    //         headers: {
    //             "Content-Type": "application/json",
    //         },
    //         body: JSON.stringify(user),
    //     });

    //     if (data.ok) {
    //         const session = await data.json();
    //         return session;
    //     } else {
    //         throw new Error("Failed to sign in");
    //     }
    // } catch (error) {
    //     console.error(error);
    //     return error;
    // }
    return mockUserSerssion;
}

export async function fetchRepositories(
    page: number,
    perPage: number
): Promise<Array<IRepository>> {
    try {
        const response = await api.get<Array<IRepository>>(
            `/projects/github?page=${page}&per_page=${perPage}`
        );
        if (response.status === 200) {
            return response.data;
        } else {
            throw new Error("Failed to fetch repositories");
        }
    } catch (error) {
        console.error(error);
        return [];
    }
}

export const getRepositoryByOwnerRepo = async (
    owner?: string,
    repo?: string
) => {
    try {
        const response = await api.get<IRepository>(
            `/projects/github/${owner}/${repo}`
        );
        if (response.status === 200) {
            return response.data;
        } else {
            throw new Error("Failed to fetch repository");
        }
    } catch (error) {
        console.error(error);
        return null;
    }
};

export async function getRepositoryStatistics(input: GetStatsSchema) {
    const {
        owner,
        repo,
        page,
        per_page,
        sort,
        commiter,
        branch,
        from,
        to,
        operator,
    } = input;

    try {
        const response = await api.get<Array<Statistics>>(
            `/projects/github/${owner}/${repo}/statistics`
        );
        if (response.status === 200) {
            console.log("response", response.data);
            return {
                data: response.data,
                pageCount: 1,
            };
        } else {
            throw new Error("Failed to fetch repository statistics");
        }
    } catch (error) {
        console.error(error);
        return {
            data: [],
            pageCount: 0,
        };
    }
}
