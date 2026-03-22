import z from "zod";

export const SignUpFormSchema = z.object({
    username: z.string().nonempty("Please enter user name."),
    email: z.email("Please enter valid email.").nonempty("Please enter email.")
})

export type SignUpForm = z.infer<typeof SignUpFormSchema>

export const SignInFormSchema = z.object({
    email: z.email("Please enter valid email for login.").nonempty("Please enter email for login."),
    password: z.string().nonempty("Please enter password.")
})

export type SignInForm = z.infer<typeof SignInFormSchema>

export const ActivationFormSchema = z.object({
    userId: z.string().nonempty("Please enter user id."),
    otpCode: z.string().nonempty("Please enter OTP code."),
    password: z.string().nonempty("Please enter password.")
})

export type ActivationForm = z.infer<typeof ActivationFormSchema>