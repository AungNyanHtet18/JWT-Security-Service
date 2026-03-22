import { Button } from "@/components/ui/button";
import { Home, Shield } from "lucide-react";
import Link from "next/link";

export default function AnnonymousLayout({children} : {children: React.ReactNode}) {
     return (
        <div className="h-screen flex">
             <header  className="flex-1 bg-zinc-600 text-white flex flex-col  justify-center items-center gap-4 ">
                 <Shield className="size-48"/>
                 <h1 className="text-3xl fw-bold uppercase">Security Demo</h1>
                 <Button asChild className="bg-white text-black">
                    <Link href={'/'}>
                        <Home/> Home
                    </Link>
                 </Button>
             </header>

             <main className="flex-1 flex justify-center items-center">
                <section className="w-1/2">
                    {children}
                </section>
             </main>
        </div>
     )
}