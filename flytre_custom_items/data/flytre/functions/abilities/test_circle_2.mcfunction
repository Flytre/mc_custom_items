####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/18/2019 07:57
####################################################################################################

particle cloud ^ ^ ^3.0 0 0 0 0 1 force
tp @s ~ ~ ~ ~25 ~
execute unless entity @s[y_rotation=0..25] at @s run function flytre:abilities/test_circle_2
