import * as z from "zod";

export const SignupValidation = z.object({
    name: z
        .string()
        .min(3, { message: "Name must be at least 3 characters long" })
        .max(255, { message: "Name must be at most 255 characters long" }),
    username: z
        .string()
        .min(3, { message: "Username must be at least 3 characters long" })
        .max(255, { message: "Username must be at most 255 characters long" }),
    email: z.string().email({ message: "Invalid email address" }),
    password: z
        .string()
        .min(8, { message: "Password must be at least 8 characters long" }),
});

export const SigninValidation = z.object({
    email: z.string().email({ message: "Invalid email address" }),
    password: z
        .string()
        .min(8, { message: "Password must be at least 8 characters long" }),
});

export const SearchParamsSchema = z.object({
    owner: z.string(),
    repo: z.string(),
    page: z.coerce.number().default(1).optional(),
    per_page: z.coerce.number().default(10).optional(),
    sort: z.string().optional(),
    commiter: z.string().optional(),
    branch: z.string().optional(),
    from: z.string().optional(),
    to: z.string().optional(),
    operator: z.enum(["and", "or"]).optional(),
});

export const getStatsSchema = SearchParamsSchema;

export type GetStatsSchema = z.infer<typeof getStatsSchema>;
