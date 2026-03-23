'use client'

import FormsInput from "@/components/forms/forms-input"
import { Button } from "@/components/ui/button"
import { SignUpForm, SignUpFormSchema } from "@/lib/form"
import { zodResolver } from "@hookform/resolvers/zod"
import { UserPlus } from "lucide-react"
import { useRouter } from "next/navigation"
import { useForm } from "react-hook-form"

export default function SignUpPage() {

    const router = useRouter()

    const form = useForm({
         resolver: zodResolver(SignUpFormSchema),
         defaultValues: {
             email: "",
             username: ""
         }
    })

    const onSignUp = (form: SignUpForm) => {
         router.replace(`/activate/1`)
    }

     return (
        <div className="space-y-4">
            <h1 className="flex items-center gap-2 font-bold">
                <UserPlus/> <span className="text-2xl">Sign Up</span>
            </h1>

            <form onSubmit={form.handleSubmit(onSignUp)} className="space-y-4">
                <FormsInput controls={form.control} path="username" label="User Name" />
                <FormsInput controls={form.control} path="email" label="Email" type="email" />
                
                <Button type="submit">
                     <UserPlus/> Sign Up
                </Button>

            </form>
        </div>
     )
}