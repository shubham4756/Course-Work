dosseg
.model small

.data
msg db "Enter a string: $"
msg1 db "Enter a substring: $"
msg2 db "The substring exists $"
msg3 db "The substring doesnt exist $"

st1 db 80 dup('$')
st2 db 80 dup('$')
cnt1 dw ?
cnt2 dw ?
;this macro will print a string terminated by $

printstring macro msg
mov ah, 09h ; write a $ terminated string to standard output    
mov dx, offset msg 
int 21h
endm

scanstring macro str 
local upe, stp
mov si, offset str
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

checkifexists macro st1,cnt1,st2,cnt2
mov cx, cnt1
mov si, offset st1
mov bx, si
up:
mov si, bx
mov di, offset st2
up1:    
mov al, [di]
cmp al, '$'
je success
cmp al, [si]
jne break
inc si;
inc di
jmp up1

break:
inc bx
loop up

printstring msg3
jmp endf

success: printstring msg2
endf:
endm

.code
mov ax, @data
mov ds, ax

printstring msg
scanstring st1
mov cnt1, cx
printstring msg1
scanstring st2
mov cnt2, cx

checkifexists st1,cnt1,st2,cnt2
mov ah, 4ch
int 21h
end