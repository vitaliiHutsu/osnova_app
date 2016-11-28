package ua.vesa.osnova.utils;

/*
  А	Б	В	Г	Д	Е	Ё	Ж	З	И	Й	К	Л	М	Н	О	П	Р	С	Т	У	Ф	Х	Ц	Ч	Ш	Щ	Ъ	Ы	Ь	Э	Ю	Я
  A	B	V	G	D	E	JE	ZH	Z	I	Y	K	L	M	N	O	P	R	S	T	U	F	KH	C	CH	SH	JSH	HH	IH	JH	EH	JU	JA
*/

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translit {

    private String slashUtils(char ch){
        switch (ch){
            case '\\': return "-";
            case '/': return "-";
            case '.': return "_";
            default: return String.valueOf(ch);
        }
    }

    private String cyr2lat(char ch){
        switch (ch){
            case 'а': return "a";
            case 'б': return "b";
            case 'в': return "v";
            case 'г': return "g";
            case 'д': return "d";
            case 'е': return "e";
            case 'ё': return "je";
            case 'ж': return "zh";
            case 'з': return "z";
            case 'и': return "i";
            case 'й': return "y";
            case 'к': return "k";
            case 'л': return "l";
            case 'м': return "m";
            case 'н': return "n";
            case 'о': return "o";
            case 'п': return "p";
            case 'р': return "r";
            case 'с': return "s";
            case 'т': return "t";
            case 'у': return "u";
            case 'ф': return "f";
            case 'х': return "kh";
            case 'ц': return "c";
            case 'ч': return "ch";
            case 'ш': return "sh";
            case 'щ': return "jsh";
            case 'ъ': return "hh";
            case 'ы': return "ih";
            case 'ь': return "jh";
            case 'э': return "eh";
            case 'ю': return "ju";
            case 'я': return "ja";
            case 'і': return "i";
            case 'ї': return "y";
            case 'ґ': return "g";
            case 'є': return "eh";
            case ' ': return "_";
            default: return String.valueOf(ch);
        }
    }

    public String cyr2lat(String value){
        String s = value.toLowerCase(Locale.ROOT);
        StringBuilder stringBuilder = new StringBuilder(s.length()*2);
        for (char ch: s.toCharArray()){
            stringBuilder.append(cyr2lat(ch));
        }

        return stringBuilder.toString();
    }

    public String replacaSlesh(String title){
        String s = title.toLowerCase(Locale.ROOT);
        StringBuilder stringBuilder = new StringBuilder(s.length()*2);
        for (char ch: s.toCharArray()){
            stringBuilder.append(slashUtils(ch));
        }

        return stringBuilder.toString();
    }
}
