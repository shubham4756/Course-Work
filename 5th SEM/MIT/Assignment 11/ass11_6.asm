dosseg
.model small

.data
msg db "Enter a string to reverse : $"
st1 db 80 dup('$')

scanstring macro str 
mov si, offset str
mov cx, 0000h
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

;this macro will print a string terminated by $
printstring macro msg
mov ah, 09h ; write a $ terminated string to standard output    
mov dx, offset msg 
int 21h
endm


reverse macro st1
    mov si, offset st1
    mov di, si
    add di, cx
    dec di
    shr cx, 1
    upc:
    mov al, [si]
    mov bl, [di]
    mov [di], al
    mov [si], bl
    dec di
    inc si
    loop upc
endm

.code
mov ax, @data
mov ds, ax


printstring msg
scanstring st1 
; cx stores the count of string length currently
reverse st1

printstring st1

mov ah, 4ch
int 21h
end