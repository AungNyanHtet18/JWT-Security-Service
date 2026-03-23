import SignOutButton from "@/components/app/signout-button";
import { User } from "lucide-react";

export default function MemberLayout({children} : {children: React.ReactNode}) {
     return (
        <div className="h-screen">

            <nav className="w-full bg-gray-600 text-white shadow px-12 py-4 flex justify-between">
                <h1 className="flex gap-2 justify-between">
                    <User/> 
                    <span className="text-xl font-semibold">Member Home</span>
                </h1> 

                <SignOutButton />              
            </nav>

            <main className="px-12 py-4">
                {children}
            </main>
        </div>
     )
}