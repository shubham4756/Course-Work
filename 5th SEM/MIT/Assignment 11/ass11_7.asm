dosseg
.model small

.data
msg db "Enter string: $"
st1 db 80 dup('$')
;this macro will print a string terminated by $
printstring macro msg
mov ah, 09h ; write a $ terminated string to standard output    
mov dx, offset msg 
int 21h
endm

scanstring macro str 
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

caseconverse macro str
mov si, offset str;
upf:
mov al, [si]
cmp al, 'a'
jb checkuppercase
cmp al, 'z'
ja skip
sub al, 20h
jmp skip
checkuppercase: 
cmp al, 'A'
jb skip
cmp al, 'Z'
ja skip
add al, 20h
skip:
mov [si],al
inc si
loop upf
endm


.code
mov ax, @data
mov ds, ax
printstring msg
scanstring st1
caseconverse st1
printstring st1

mov ah, 4ch
int 21h
end