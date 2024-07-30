import {
    useQuery,
    useMutation,
    useQueryClient,
    useInfiniteQuery,
} from "@tanstack/react-query";
import {
    createUserAccount,
    fetchRepositories,
    getRepositoryByOwnerRepo,
    getRepositoryStatistics,
    signInAccount,
    signOutAccount,
} from "../gitparse/api";
import { INewUser } from "@/types";
import { GetStatsSchema } from "../validation";
export const useCreateUserAccount = () => {
    return useMutation({
        mutationFn: (user: INewUser) => createUserAccount(user),
    });
};

export const useSignInAccount = () => {
    return useMutation({
        mutationFn: (user: { email: string; password: string }) =>
            signInAccount(user),
    });
};

export const useSignOutAccount = () => {
    return useMutation({
        mutationFn: () => signOutAccount(),
    });
};

export const useGetAllRepositories = () => {
    return useMutation({
        mutationFn: (pageRequest: { page: number; perPage: number }) =>
            fetchRepositories(pageRequest.page, pageRequest.perPage),
    });
};

export const useGetRepositoryByOwnerRepo = (owner?: string, repo?: string) => {
    return useQuery({
        queryKey: ["getRepositoryByOwnerRepo", owner, repo],
        queryFn: () => getRepositoryByOwnerRepo(owner, repo),
    });
};

// export const useGetStatistics = (input: GetStatsSchema) => {
//     return useQuery({
//         queryKey: ["getStatistics", input],
//         queryFn: () => getTasks(input),
//     });
// };

// export const useGetRepositoryStatistics = (owner?: string, repo?: string) => {
//     return useMutation({
//         mutationFn: (pageRequest: { page: number; perPage: number }) =>
//             getRepositoryStatistics({
//                 owner,
//                 repo,
//                 page: pageRequest.page,
//                 perPage: pageRequest.perPage,
//             }),
//     });
// };
