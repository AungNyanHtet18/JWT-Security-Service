'use client'

import FormsInput from "@/components/forms/forms-input"
import { Button } from "@/components/ui/button"
import { SignInForm, SignInFormSchema } from "@/lib/form"
import { zodResolver } from "@hookform/resolvers/zod"
import { LogIn, Unlock, UnlockIcon } from "lucide-react"
import { useRouter } from "next/navigation"
import { useForm } from "react-hook-form"

export default function SignInPage() {

    const router = useRouter()

    const form = useForm<SignInForm>({ 
        resolver: zodResolver(SignInFormSchema),
        defaultValues: {
           email: '',
           password: ''
        }
    })

    const signInAction = (form: SignInForm) => {
        const routerUrl = `/${form.password.toLocaleLowerCase()}`
        router.replace(routerUrl)
    }

    return (
        <div>
            <h1 className="flex items-center gap-2">
                <LogIn/> <span className="text-2xl">Sign In</span>
            </h1>

            <form onSubmit={form.handleSubmit(signInAction)} className="space-y-4">
                <FormsInput controls={form.control} path="email" label="Login ID" />
                <FormsInput controls={form.control} path="password" label="Password" />

                <nav>
                    <Button type="submit">
                        <Unlock/> Sign In
                    </Button>
                </nav>

            </form>

        </div>
     )
}