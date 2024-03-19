import { type Metadata } from 'next';
import 'the-new-css-reset/css/reset.css';
import './index.css';
import React from 'react';

export const metadata: Metadata = {
  title: 'Todoリスト'
};

const RootLayout: React.FC<
  Readonly<{
    children: React.ReactNode;
  }>
> = ({ children }) => {
  return (
    <html lang="ja">
      <body className="--font-note">{children}</body>
    </html>
  );
};

export default RootLayout;
