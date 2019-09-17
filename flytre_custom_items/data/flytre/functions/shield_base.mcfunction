scoreboard players set @a[scores={shieldblock=1..}] shieldblock 0
####################################################################################################
#Automatically Generated File
#Created Using: Flytre's Custom Item Generator
#Created: 09/17/2019 18:35
####################################################################################################

execute as @a[scores={shieldblock=1..},nbt={SelectedItem:{tag:{ability:"lit"}}}] at @s run effect give @s regeneration 1 1 true
execute as @a[nbt={SelectedItem:{tag:{ability:"lit"}}}] at @s run effect give @e[type=!player,distance=..4] wither 1 1 true
execute as @a[nbt={Inventory:[{Slot: -106b, tag:{ability:"lit"}}]}] at @s run effect give @e[type=!player,distance=..4] wither 1 1 true
scoreboard players set @a[scores={shieldblock=1..}] shieldblock 0
