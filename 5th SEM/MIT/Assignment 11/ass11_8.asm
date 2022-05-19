dosseg
.model small
.data
msg db "Enter a string: $";
st1 db 80 dup('$');
st2 db 80 dup('$')
cnt1 dw 0
cnt2 dw 0

;this macro will print a string terminated by $
printstring macro msg
mov ah, 09h ; write a $ terminated string to standard output    
mov dx, offset msg 
int 21h
endm

scanstring macro str 
local @@up2, @@skip1
mov si, offset str
mov cx, 0000h
@@up2:
mov ah, 01h ;scan
int 21h
cmp al, 0dh ;cmp with carriage return
je @@skip1
mov [si], al
inc si
inc cx;
jmp @@up2
@@skip1:
endm

mergestring macro st1,cnt1,st2,cnt2
    mov di, offset st1
    add di, cnt1
    mov si, offset st2
    mov cx, cnt2
    upf:
    mov al, [si];
    mov [di], al;
    inc si;
    inc di;
    loop upf
endm

.code

mov ax, @data
mov ds, ax

printstring msg
scanstring st1
mov cnt1, cx;

printstring msg

scanstring st2
mov cnt2, cx;

mergestring st1,cnt1,st2,cnt2
printstring st1

mov ah, 4ch
int 21h
end