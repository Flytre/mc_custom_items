####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/17/2019 18:35
####################################################################################################

summon armor_stand ~ ~ ~ {Tags:[test_c],NoGravity:1b,Small:1,Marker:1b,Invisible:1,Invulnerable:1,NoBasePlate:1,PersistenceRequired:1,DisabledSlots:2039583}
execute as @e[tag=test_c,type=armor_stand] at @s run tp @s ~ ~ ~ 26 0
execute as @e[tag=test_c,type=armor_stand] at @s run function flytre:abilities/test_circle_2
kill @e[type=armor_stand,tag=test_c]
