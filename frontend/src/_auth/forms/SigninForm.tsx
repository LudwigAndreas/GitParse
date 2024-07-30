import * as z from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { Link, useNavigate } from "react-router-dom";

import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import { useToast } from "@/components/ui/use-toast";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { useForm } from "react-hook-form";

import Loader from "@/components/shared/Loader";
import { SigninValidation } from "@/lib/validation";
import { useSignInAccount } from "@/lib/react-query/queriesAndMutations";
import { useUserContext } from "@/context/AuthContext";

const SignupForm = () => {
    // Hooks
    const { toast } = useToast();
    const { checkAuthUser, isLoading: isUserLoading } = useUserContext();
    const navigate = useNavigate();

    const form = useForm<z.infer<typeof SigninValidation>>({
        resolver: zodResolver(SigninValidation),
        defaultValues: {
            email: "",
            password: "",
        },
    });

    // Queries
    const { mutateAsync: signInAccount, isPending: isSigningIn } =
        useSignInAccount();

    async function onSubmit(values: z.infer<typeof SigninValidation>) {
        const session = await signInAccount({
            email: values.email,
            password: values.password,
        });

        if (!session) {
            toast({
                variant: "destructive",
                title: "Sign in failed. Please sign in again.",
                description: "Failed to sign in",
            });

            navigate("/sign-in");

            return;
        }

        const isLoggedIn = await checkAuthUser();

        if (isLoggedIn) {
            form.reset();
            navigate("/");
        } else {
            return toast({
                variant: "destructive",
                title: "Sign in failed. Please try again. ",
                description: "Failed to sign in",
            });
        }
    }

    return (
        <Form {...form}>
            <div className="sm:w-420 flex-center flex-col">
                <img
                    src="/assets/images/logo.svg"
                    alt="logo"
                    className="w-2/3"
                />
                <h2 className="h3-bold md:h2-bold pt-5 sm:pt-12">
                    Log in to your account
                </h2>
                <p className="text-light-3 small-medium md:base-regular mt-2 text-center ">
                    To use Gitparse Admin panel, you need to sign in.
                </p>

                <form
                    onSubmit={form.handleSubmit(onSubmit)}
                    className="flex flex-col gap-5 w-full mt-4">
                    <FormField
                        control={form.control}
                        name="email"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Email</FormLabel>
                                <FormControl>
                                    <Input
                                        type="email"
                                        className="shad-input "
                                        placeholder="Your email address"
                                        {...field}
                                    />
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                    <FormField
                        control={form.control}
                        name="password"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Password</FormLabel>
                                <FormControl>
                                    <Input
                                        type="password"
                                        className="shad-input "
                                        placeholder="Your password"
                                        {...field}
                                    />
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                    <Button type="submit" className="shad-button_primary">
                        {isSigningIn || isUserLoading ? (
                            <div className="flex-center gap-2">
                                <Loader />
                                Loading...
                            </div>
                        ) : (
                            "Sign up"
                        )}
                    </Button>
                    <p className="text-small-regular text-light-2 text-center mt-2">
                        Need an account?{" "}
                        <Link
                            to="/sign-up"
                            className="text-primary-500 text-small-semibold">
                            Register
                        </Link>
                    </p>
                </form>
            </div>
        </Form>
    );
};

export default SignupForm;
