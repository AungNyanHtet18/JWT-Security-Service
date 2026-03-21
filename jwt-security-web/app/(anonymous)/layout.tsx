export default function AnnonymousLayout({children} : {children: React.ReactNode}) {
     return (
        <div>
            <h1>Annonymous Page</h1>
            {children}
        </div>
     )
}