import React from 'react';
import '../index.css';

const Header: React.FC<{ h1Text: string }> = ({ h1Text }) => {
  return (
    <header className="my-2">
      <h1 className="text-center text-5xl font-black">{h1Text}</h1>
    </header>
  );
};

export default Header;
