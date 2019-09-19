####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/18/2019 07:57
####################################################################################################

summon armor_stand ~ ~ ~ {Tags:[circler_c],NoGravity:1b,Small:1,Marker:1b,Invisible:1,Invulnerable:1,NoBasePlate:1,PersistenceRequired:1,DisabledSlots:2039583}
execute as @e[tag=circler_c,type=armor_stand] at @s run tp @s ~ ~ ~ 4 0
execute as @e[tag=circler_c,type=armor_stand] at @s run function flytre:abilities/circler_circle_2
kill @e[type=armor_stand,tag=circler_c]
