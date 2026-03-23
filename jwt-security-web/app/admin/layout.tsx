import SignOutButton from "@/components/app/signout-button";
import { LayoutDashboard, User } from "lucide-react";

export default function AdminLayout({children} : {children: React.ReactNode}) {
     return (
        <div className="h-screen">

            <nav className="w-full bg-gray-900 text-white shadow px-12 py-4 flex justify-between">
                <h1 className="flex gap-2 justify-between">
                    <LayoutDashboard/> 
                    <span className="text-xl font-semibold">Admin Home</span>
                </h1> 

                <SignOutButton />              
            </nav>

            <main className="px-12 py-4">
                {children}
            </main>
        </div>
     )
}