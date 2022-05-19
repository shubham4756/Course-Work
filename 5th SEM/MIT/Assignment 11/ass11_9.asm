dosseg
.model small

.data
msg db "Enter a string: $"
msg2 db "Enter a key: $"
key db ?
pos db ?
st1 db 80 dup('$')

;print a character
printchar macro ch
mov ah, 02h
mov dl, ch
int 21h
endm

;print a 2 dig number
print2 macro num
printchar 0dh
printchar 0ah
mov al, num
add al, 0h
daa
mov bl, al
and al, 0F0h
shr al, 04h
add al,30h
printchar al
and bl, 0Fh
add bl,30h 
printchar bl
endm

;this macro will print a string terminated by $
printstring macro msg
mov ah, 09h ; write a $ terminated string to standard output    
mov dx, offset msg 
int 21h
endm


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

givepos macro st1, key, pos
mov si, offset st1
mov pos, 0
up:
mov al, [si];
cmp al, key
je quit
inc pos
inc si
loop up
quit:
endm

.code
mov ax, @data
mov ds, ax

printstring msg
scanstring st1
printstring msg2
mov ah, 01h
int 21h
mov key, al;

givepos st1, key, pos
print2 pos

mov ah, 4ch
int 21h
end