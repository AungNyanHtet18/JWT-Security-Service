'use client'

import FormsInput from "@/components/forms/forms-input";
import { Button } from "@/components/ui/button";
import { ActivationForm, ActivationFormSchema } from "@/lib/form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Check, UserCheck } from "lucide-react";
import { useParams, useRouter } from "next/navigation";
import { useEffect } from "react";
import { useForm } from "react-hook-form";

export default function ActivationPage() {

    const router = useRouter()
    const {id} = useParams() // useParams for path parameter

    const form = useForm({
         resolver: zodResolver(ActivationFormSchema),
         defaultValues: {
             otpCode: "",
             password: ""
         }
    })

    const onActivate = (form : ActivationForm) => {
         const ActivationForm = {userId: id, ...form}
         console.log(form)
         router.replace("/member")
    }

     return (
        <div className="space-y-6">
            <h1 className="flex items-center gap-2 font-bold">
                <UserCheck/> <span className="text-2xl">Activate Account</span>
            </h1>

            <form onSubmit={form.handleSubmit(onActivate)} className="space-y-4">
                <FormsInput controls={form.control} path="otpCode" label="Code send by eamil" />
                <FormsInput controls={form.control} path="password" type="password" label="Your Password" />
                <FormsInput controls={form.control} path="comfirmPassword" type="password" label="Comfirm Password" />

                <Button type="submit"> 
                    <Check/>Activate
                </Button>
            </form>
        </div>
     )
}