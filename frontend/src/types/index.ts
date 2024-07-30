export type IContextType = {
    user: IUser;
    isLoading: boolean;
    setUser: React.Dispatch<React.SetStateAction<IUser>>;
    isAuthenticated: boolean;
    setIsAuthenticated: React.Dispatch<React.SetStateAction<boolean>>;
    checkAuthUser: () => Promise<boolean>;
};

export type IUser = {
    id: string;
    name: string;
    username: string;
    email: string;
    imageUrl: string;
    bio: string;
};

export type INewUser = {
    name: string;
    username: string;
    email: string;
    password: string;
};

export type INavLink = {
    imgUrl: string;
    route: string;
    label: string;
};

export type IRepository = {
    id: string;
    name: string;
    description: string;
    createdAt: string;
    defaultBranch: string;
    webUrl: string;
    avatarUrl: string;
    ownerUsername: string;
};

export interface PaginatedResponse<T> {
    items: T[];
    total_count: number;
    next_page: number | null;
}

export type IStats = {
    additions: number;
    deletions: number;
    total: number;
};

export type IShallowGitUser = {
    id: number;
    name: string;
    username: string;
    avatarUrl: string;
    url: string;
};

export type IBranch = {
    name: string;
    url: string;
    // commit: ICommit;
};

export type Statistics = {
    sha: string;
    url: string;
    message: string;
    stats: IStats;
    datetime: string;
    committer: IShallowGitUser;
    branch: IBranch;
};

export interface SearchParams {
    [key: string]: string | string[] | undefined;
}

export interface Option {
    label: string;
    value: string;
    icon?: React.ComponentType<{ className?: string }>;
    withCount?: boolean;
}

export interface DataTableFilterField<TData> {
    label: string;
    value: keyof TData;
    placeholder?: string;
    options?: Option[];
}

export interface DataTableFilterOption<TData> {
    id: string;
    label: string;
    value: keyof TData;
    options: Option[];
    filterValues?: string[];
    filterOperator?: string;
    isMulti?: boolean;
}
