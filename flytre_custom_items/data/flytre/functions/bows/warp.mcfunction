####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/17/2019 18:35
####################################################################################################

execute as @s run execute as @a run scoreboard players operation @s enderID3 = @s enderID
execute as @s run scoreboard players operation @a enderID3 -= @s enderID
tag @a[scores={enderID3=0}] add bow_warp
execute as @s run tp @a[tag=bow_warp] @s
tag @a remove bow_warp
scoreboard players reset * enderID3
tag @s add effectDone
execute as @e[type=armor_stand,tag=warp,tag=!effectDone,scores={arrowDetection=3},sort=random,limit=1] at @s run function flytre:bows/warp
