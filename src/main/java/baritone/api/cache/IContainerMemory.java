/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.api.cache;

import net.minecraft.util.math.BlockPos;

import java.util.Map;

/**
 * @author Brady
 * @since 9/23/2018
 */
public interface IContainerMemory {

    /**
     * Gets a remembered inventory by its block position.
     *
     * @param pos The position of the container block
     * @return The remembered inventory
     */
    IRememberedInventory getInventoryByPos(BlockPos pos);

    /**
     * Gets the map of all block positions to their remembered inventories.
     *
     * @return Map of block positions to their respective remembered inventories
     */
    Map<BlockPos, IRememberedInventory> getRememberedInventories();
}
