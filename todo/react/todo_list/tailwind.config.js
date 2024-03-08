/** @type {import('tailwindcss').Config} */

const config = {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {},
    fontFamily: {
      noto: ['Noto Sans', 'Noto Sans JP', 'Noto Color Emoji', 'sans-serif']
    }
  },
  plugins: []
};

export default config;
