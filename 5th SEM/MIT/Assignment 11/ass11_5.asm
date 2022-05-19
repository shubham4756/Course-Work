dosseg
.model small

.data
msg db "Enter string: $"
st1 db 80 dup('$')
st2 db 80 dup('$')
;this macro will print a string terminated by $
printstring macro msg
mov ah, 09h ; write a $ terminated string to standard output    
mov dx, offset msg 
int 21h
endm

scanstring macro st 
mov si, offset st
mov cx,0000h
upe:
mov ah, 01h ;scan
int 21h

cmp al, 0dh ;cmp with carriage return
je stp
mov [si], al
inc si
inc cx
jmp upe

stp:
endm

comparestrings macro st1, st2, cnt 
    mov si, offset st1
    mov cx, cnt
    mov di, offset st2
    upb:
    mov al, [si]
    mov [di], al
    inc si
    inc di
    loop upb
endm


.code

mov ax, @data
mov ds, ax

printstring msg
scanstring st1

mov si, offset st1

comparestrings st1, st2, cx

printstring st2

mov ah, 4ch
int 21h
end