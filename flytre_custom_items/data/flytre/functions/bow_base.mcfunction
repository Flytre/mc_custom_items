####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/18/2019 07:57
####################################################################################################

execute as @a store result score @s enderID run data get entity @s UUIDLeast 0.00000000001
execute as @e[type=arrow] at @s store result score @s enderID run data get entity @s OwnerUUIDLeast 0.00000000001
execute as @a[nbt={SelectedItem:{tag:{ability:"explosive"}}}] at @s run execute as @e[type=arrow,distance=..10,tag=!init,limit=1] at @s run function flytre:bow_arrow_init
execute as @e[type=arrow,tag=explosive,nbt={inGround:0b}] at @s positioned ~ ~.5 ~ run particle flame ~ ~-0.06 ~0 0 0.12 0 0 30 force
execute as @e[type=arrow,tag=explosive,nbt={inGround:0b}] at @s run kill @e[type=armor_stand,distance=..8,tag=explosive,limit=1]
execute as @e[type=arrow,tag=explosive,nbt={inGround:0b}] at @s run summon armor_stand ~ ~1 ~ {Tags:[explosive,arrow_stand],NoGravity:1b,Small:1,Marker:1b,Invisible:1,Invulnerable:1,NoBasePlate:1,PersistenceRequired:1,DisabledSlots:2039583}
execute as @e[type=arrow,tag=explosive] at @s store result score @e[type=armor_stand,tag=explosive,limit=1,distance=..8] enderID run data get entity @s OwnerUUIDLeast 0.00000000001
execute as @e[type=armor_stand,tag=explosive,scores={arrowDetection=3}] at @s run summon fireball ~ ~ ~ {direction:[0.0,-100.0,0.0],ExplosionPower:5.5,Motion:[0.0,-1.0,0.0]}
execute as @a[nbt={SelectedItem:{tag:{ability:"warp"}}}] at @s run execute as @e[type=arrow,distance=..10,tag=!init,limit=1] at @s run function flytre:bow_arrow_init
execute as @e[type=arrow,tag=warp,nbt={inGround:0b}] at @s positioned ~ ~.5 ~ run particle portal ~ ~-0.06 ~0 0 0.12 0 0 30 force
execute as @e[type=arrow,tag=warp,nbt={inGround:0b}] at @s run kill @e[type=armor_stand,distance=..8,tag=warp,limit=1]
execute as @e[type=arrow,tag=warp,nbt={inGround:0b}] at @s run summon armor_stand ~ ~1 ~ {Tags:[warp,arrow_stand],NoGravity:1b,Small:1,Marker:1b,Invisible:1,Invulnerable:1,NoBasePlate:1,PersistenceRequired:1,DisabledSlots:2039583}
execute as @e[type=arrow,tag=warp] at @s store result score @e[type=armor_stand,tag=warp,limit=1,distance=..8] enderID run data get entity @s OwnerUUIDLeast 0.00000000001
execute as @e[type=armor_stand,tag=warp,scores={arrowDetection=3}] at @s run function flytre:bows/warp
tag @e[type=arrow] add init
scoreboard players add @e[type=armor_stand,tag=arrow_stand] arrowDetection 1
kill @e[type=arrow,tag=custom_arrow,nbt={inGround:1b}]
kill @e[type=armor_stand,tag=arrow_stand,scores={arrowDetection=4..}]
