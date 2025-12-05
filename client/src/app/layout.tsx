import "@/app/globals.css";
import type { Metadata } from "next";
import { ReactNode } from "react";

export const metadata: Metadata = {
  title: "Eigakan - Home",
  description: "Home",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: ReactNode;
}>) {
  return (
    <html lang="pt-BR">
      <body className="antialiased flex">
        <main className="flex-1">{children}</main>
      </body>
    </html>
  );
}
