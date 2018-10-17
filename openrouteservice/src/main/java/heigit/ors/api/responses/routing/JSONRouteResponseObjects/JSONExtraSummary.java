/*
 * This file is part of Openrouteservice.
 *
 * Openrouteservice is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this library;
 * if not, see <https://www.gnu.org/licenses/>.
 */

package heigit.ors.api.responses.routing.JSONRouteResponseObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class JSONExtraSummary {
    private double value;
    private double distance;
    private double amount;

    public JSONExtraSummary(double value, double distance, double amount) {
        this.value = value;
        this.distance = distance;
        this.amount = amount;
    }

    @ApiModelProperty(value = "[Value](https://github.com/GIScience/openrouteservice-docs#routing-response) of a info category.")
    @JsonProperty("value")
    public double getValue() {
        return value;
    }

    @ApiModelProperty(value = "Cumulative distance of this value.")
    @JsonProperty("distance")
    public double getDistance() {
        return distance;
    }

    @ApiModelProperty(value = "Category percentage of the entire route.")
    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }
}
