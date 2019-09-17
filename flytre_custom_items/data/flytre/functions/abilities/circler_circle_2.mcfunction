####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/17/2019 18:35
####################################################################################################

particle flame ^ ^ ^20.0 0 0 0 0 1 force
tp @s ~ ~ ~ ~3 ~
execute unless entity @s[y_rotation=0..3] at @s run function flytre:abilities/circler_circle_2
