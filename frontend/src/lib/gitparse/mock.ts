import { Statistics } from "@/types";

export const mockUser = {
    id: "123",
    name: "Test User",
    username: "testuser",
    email: "testuser@example.com",
    imageUrl: "",
    bio: "This is a test user",
};

export const mockUserSerssion = {
    token: "1234567890",
    userId: "123",
};

export const signInConfirm = {
    success: true,
    userId: "123",
};

export const mockRepos = [
    {
        id: "816462906",
        name: "Forum-REST-API-Backend",
        createdAt: "2024-06-17T23:05:04.000+03:00",
        defaultBranch: "master",
        url: "https://github.com/LudwigAndreas/Forum-REST-API-Backend",
        ownerAvatar: "https://avatars.githubusercontent.com/u/88089961?v=4",
        ownerLogin: "LudwigAndreas",
        platform: "github",
    },
    {
        id: "816406377",
        name: "Backend-Academy-2023",
        createdAt: "2024-06-17T20:25:04.000+03:00",
        defaultBranch: "master",
        url: "https://github.com/LudwigAndreas/Backend-Academy-2023",
        ownerAvatar: "https://avatars.githubusercontent.com/u/88089961?v=4",
        ownerLogin: "LudwigAndreas",
        platform: "github",
    },
];

export const mockBranches = [
    {
        name: "master",
        url: "https://github.com/LudwigAndreas/Forum-REST-API-Backend/tree/master",
    },
    {
        name: "develop",
        url: "https://github.com/LudwigAndreas/Forum-REST-API-Backend/tree/develop",
    },
    {
        name: "feature/add-user-service-interface",
        url: "https://github.com/LudwigAndreas/Forum-REST-API-Backend/tree/feature/add-user-service-interface",
    },
];

const statsDataSample = [
    {
        additions: 10,
        deletions: 5,
        total: 15,
    },
    {
        additions: 5,
        deletions: 2,
        total: 7,
    },
    {
        additions: 20,
        deletions: 10,
        total: 30,
    },
];

const userSample = [
    {
        id: 1,
        name: "Ludwig Andreas",
        username: "LudwigAndreas",
        avatarUrl: "https://avatars.githubusercontent.com/u/88089961?v=4",
        url: "https://github.com/LudwigAndreas",
    },
    {
        id: 2,
        name: "Krmakov Eduard",
        username: "RemasLover13",
        avatarUrl: "https://avatars.githubusercontent.com/u/55665862?v=4",
        url: "https://github.com/RemasLover13",
    },
    {
        id: 3,
        name: "Vitaliy",
        username: "VitaliyK2003R",
        avatarUrl: "https://avatars.githubusercontent.com/u/90642200?v=4",
        url: "https://github.com/VitaliyK2003R",
    },
];

export const mockStats: Statistics[] = Array.from(Array(100).keys()).map(() => {
    const randomUser =
        userSample[Math.floor(Math.random() * userSample.length)];
    const randomStats =
        statsDataSample[Math.floor(Math.random() * statsDataSample.length)];
    const randomBranch =
        mockBranches[Math.floor(Math.random() * mockBranches.length)];
    return {
        stats: {
            additions: randomStats.additions,
            deletions: randomStats.deletions,
            total: randomStats.total,
        },
        user: {
            id: randomUser.id,
            name: randomUser.name,
            username: randomUser.username,
            avatarUrl: randomUser.avatarUrl,
            url: randomUser.url,
        },
        branch: {
            name: randomBranch.name,
            url: randomBranch.url,
        },
        dateTime: new Date().toISOString(),
    };
});
