dosseg
.model small

.data
msg db "Enter a string: $"
msg2 db "Enter a key: $"
st1 db 80 dup('$')
key db ?
cnt db ?
n dw ?
;this macro will print a string terminated by $
printstring macro msg
mov ah, 09h ; write a $ terminated string to standard output    
mov dx, offset msg 
int 21h
endm

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

scanstring macro str 
mov si, offset str
mov cx, 0000
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

findoccurence macro st1,n, key, cnt
mov si, offset st1
mov cx, n
mov cnt,0000h
upd: 
mov al, [si];
cmp al, key
jnz skip2
inc cnt
skip2:
inc si
loop upd

endm

.code
mov ax, @data
mov ds, ax
printstring msg
scanstring st1
mov n, cx
printstring msg2

mov ah, 01
int 21h
mov key, al

findoccurence st1,n, key, cnt
print2 cnt

mov ah, 4ch
int 21h
end