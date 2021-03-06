require('fixin')

local interface = require('interface')
local inventory = require('inventory')
local items     = require('items')
local command   = require('command')
local songs     = require('songs')

-- Load the configurations
inventory:load_config('inventory_config.json')
items:load_config('item_config.json')
songs:load_config('song_config.json')

-- Load the interface configurations and scripts
interface:load('interface_config.json')

-- // The joys of calling Silab a noob // --

local player_helper = require('plr/player_helper')

-- Load the player variables
player_helper:load_variables('plr_var_config.json')
player_helper:load_bit_variables('plr_varbit_config.json')

-- Load the commands
command:load()