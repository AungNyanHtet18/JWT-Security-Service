import { Button } from "@/components/ui/button";
import { LogIn, Shield, UserPlus } from "lucide-react";
import Link from "next/link";

export default function Home() {
   return (
     <div className="h-screen flex flex-col justify-center items-center gap-3">
        <header>
          <div className="flex items-center justify-center">
            <Shield size={100}/>
          </div>
           
           <h1 className="uppercase text-3xl font-semibold">Security Demo</h1>
        </header>

        <nav className="space-x-2">
          <Button asChild>
             <Link href={'signin'}>
                <LogIn /> Sign In
             </Link>
          </Button>

          <Button asChild>
              <Link href={'signup'}>
                  <UserPlus/> Sign Up
              </Link>
          </Button>
        </nav>
     </div>
   )
}