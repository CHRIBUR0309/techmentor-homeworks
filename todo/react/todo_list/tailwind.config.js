/** @type {import('tailwindcss').Config} */

const config = {
  content: ['./src/**/*.{html,js,jsx,ts,tsx}'],
  corePlugins: {
    preflight: false
  },
  theme: {
    extend: {},
    fontFamily: {
      noto: [
        '"Noto Sans"',
        '"Noto Sans JP"',
        '"Noto Color Emoji"',
        'sans-serif'
      ]
    }
  },
  plugins: []
};

export default config;
