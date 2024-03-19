import type { Config } from 'tailwindcss';

const config: Config = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}'
  ],
  theme: {
    extend: {},
    fontFamily: {
      noto: [
        'var(--font-notosans)',
        'var(--font-notosansjp)',
        'var(--font-notocoloremoji)',
        'sans-serif'
      ]
    }
  },
  plugins: []
};
export default config;
