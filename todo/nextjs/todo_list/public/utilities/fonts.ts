import { Noto_Color_Emoji, Noto_Sans, Noto_Sans_JP } from 'next/font/google';

const notoSans = Noto_Sans({
  weight: ['400', '700', '900'],
  subsets: ['latin'],
  variable: '--font-notosans',
  display: 'swap'
});

const notoSansJP = Noto_Sans_JP({
  weight: ['400', '700', '900'],
  subsets: ['latin'],
  variable: '--font-notosansjp',
  display: 'swap'
});

const notoColorEmoji = Noto_Color_Emoji({
  weight: '400',
  subsets: ['emoji'],
  variable: '--font-notocoloremoji',
  display: 'swap'
});

export { notoColorEmoji, notoSans, notoSansJP };
