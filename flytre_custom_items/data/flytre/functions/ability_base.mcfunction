####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/18/2019 07:57
####################################################################################################

scoreboard players add @a[nbt={SelectedItem:{tag:{ability:"test"}}}] test_cd 0
scoreboard players add @a[scores={test_cd=..-1}] test_cd 1
scoreboard players remove @a[scores={test_cd=..-1}] test_cd 37
execute as @a store result entity @s Inventory[{tag:{ability:"test"}}].tag.Damage int -0.02667 run scoreboard players get @s test_cd
scoreboard players add @a[scores={test_cd=..-1}] test_cd 37
execute as @a[scores={rightclick=1..,test_cd=0},nbt={SelectedItem:{tag:{ability:"test"}}}] at @s run function flytre:abilities/test
execute as @a[scores={test_cd=-900..-860}] at @s run function flytre:abilities/test_time
scoreboard players add @a[nbt={SelectedItem:{tag:{ability:"circler"}}}] circler_cd 0
scoreboard players add @a[scores={circler_cd=..-1}] circler_cd 1
scoreboard players remove @a[scores={circler_cd=..-1}] circler_cd 16
execute as @a store result entity @s Inventory[{tag:{ability:"circler"}}].tag.Damage int -0.06000 run scoreboard players get @s circler_cd
scoreboard players add @a[scores={circler_cd=..-1}] circler_cd 16
execute as @a[scores={rightclick=1..,circler_cd=0},nbt={SelectedItem:{tag:{ability:"circler"}}}] at @s run function flytre:abilities/circler
execute as @a[scores={circler_cd=-400..-20}] at @s run function flytre:abilities/circler_time
scoreboard players set @a[scores={rightclick=1..}] rightclick 0
scoreboard players set @a custom_item 0
