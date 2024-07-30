import { Routes, Route } from "react-router-dom";

import { Toaster } from "@/components/ui/toaster";

import SigninForm from "./_auth/forms/SigninForm";
import SignupForm from "./_auth/forms/SignupForm";
import AuthLayout from "./_auth/AuthLayout";
import RootLayout from "./_root/RootLayout";
import RepositoryLayout from "./_root/RepositoryLayout";
import {
    Home,
    Profile,
    RepositoriesMenu,
    RepositoryChart,
    RepositoryIssues,
    RepositoryPipelines,
    RepositoryStatistics,
} from "./_root/pages";
import "./globals.css";

const App = () => {
    return (
        <main className="flex h-screen">
            <Routes>
                {/* public routes */}
                <Route element={<AuthLayout />}>
                    <Route path="/sign-in" element={<SigninForm />} />
                    <Route path="/sign-up" element={<SignupForm />} />
                </Route>

                {/* private routes */}
                <Route element={<RootLayout />}>
                    <Route index element={<Home />} />
                    <Route path="/profile/:id/*" element={<Profile />} />
                    <Route
                        path="/repositories"
                        element={<RepositoriesMenu />}
                    />
                    <Route element={<RepositoryLayout />}>
                        <Route
                            path="/repository/:owner/:repo/user-chart"
                            element={<RepositoryChart />}
                        />
                        <Route
                            path="/repository/:owner/:repo/issues"
                            element={<RepositoryIssues />}
                        />
                        <Route
                            path="/repository/:owner/:repo/stats"
                            element={<RepositoryStatistics />}
                        />
                        <Route
                            path="/repository/:owner/:repo/pipelines"
                            element={<RepositoryPipelines />}
                        />
                    </Route>
                    {/* <Route path="/explore" element={<Explore />} /> */}
                    {/* <Route path="/explore" element={<Explore />} /> */}
                    {/* <Route path="/explore" element={<Explore />} /> */}
                </Route>
            </Routes>

            <Toaster />
        </main>
    );
};

export default App;
