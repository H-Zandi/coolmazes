package ir.coolapps.coolmazes;

import java.util.List;
import java.util.LinkedList;
import static ir.coolapps.coolmazes.Wall.*;

public final class MapDesigns {

	public static final List<MapDesign> designList = new LinkedList<MapDesign>();

	static {
		/*
		 * // This is the icon (flipped horizontally!) designList.add(new
		 * MapDesign( "Icon", 3, 3, new int[][] { {LEFT|TOP|RIGHT, TOP,
		 * TOP|RIGHT}, {LEFT|BOTTOM, 0, RIGHT|BOTTOM}, {LEFT|BOTTOM, BOTTOM,
		 * RIGHT|BOTTOM} }, new int[][] { {0, 0, 0}, {0, 0, 0}, {0, 0, 1} }, 0,
		 * 0 ));
		 */

		designList
				.add(new MapDesign("1", 5, 5, new int[][] {
						{ LEFT | TOP, TOP | BOTTOM, TOP | RIGHT, TOP,
								TOP | RIGHT },
						{ LEFT, 0, 0, 0, RIGHT | BOTTOM },
						{ LEFT | RIGHT, 0, BOTTOM, 0, RIGHT },
						{ LEFT, BOTTOM, 0, 0, RIGHT | BOTTOM },
						{ LEFT | BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM,
								RIGHT | BOTTOM } },
						new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 1 } }, 0, 0));

		designList.add(new MapDesign("2", 6, 6, new int[][] {
				{ LEFT | TOP | RIGHT, TOP, TOP | BOTTOM, TOP, TOP | RIGHT,
						TOP | RIGHT },
				{ LEFT, 0, 0, 0, BOTTOM, RIGHT },
				{ LEFT | RIGHT, RIGHT, RIGHT | BOTTOM, BOTTOM, 0,
						RIGHT | BOTTOM },
				{ LEFT | BOTTOM, 0, RIGHT, RIGHT, 0, RIGHT },
				{ LEFT, 0, 0, 0, 0, RIGHT | BOTTOM },
				{ LEFT | BOTTOM, BOTTOM | RIGHT, BOTTOM | RIGHT, BOTTOM,
						BOTTOM, RIGHT | BOTTOM } }, new int[][] {
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } }, 2, 2));

		designList.add(new MapDesign("3", 6, 6, new int[][] {
				{ LEFT | TOP, TOP, TOP | RIGHT | BOTTOM, TOP, TOP,
						TOP | RIGHT | BOTTOM },
				{ LEFT | BOTTOM, 0, BOTTOM, 0, 0, RIGHT },
				{ LEFT, 0, 0, 0, RIGHT, RIGHT | BOTTOM },
				{ LEFT, 0, RIGHT, BOTTOM, 0, RIGHT },
				{ LEFT | RIGHT | BOTTOM, 0, 0, 0, 0, RIGHT },
				{ LEFT | BOTTOM, BOTTOM | RIGHT, BOTTOM, BOTTOM | RIGHT,
						BOTTOM | RIGHT, BOTTOM | RIGHT } }, new int[][] {
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } }, 0, 3));

		designList.add(new MapDesign("4", 7, 7, new int[][] {
				{ LEFT | TOP, TOP, TOP, TOP | BOTTOM, TOP | RIGHT, TOP,
						TOP | RIGHT },
				{ LEFT | BOTTOM, RIGHT, 0, 0, BOTTOM, 0, RIGHT | BOTTOM },
				{ LEFT | BOTTOM, 0, RIGHT, 0, 0, 0, RIGHT },
				{ LEFT, 0, 0, 0, RIGHT | BOTTOM, 0, RIGHT | BOTTOM },
				{ LEFT, BOTTOM, RIGHT, 0, 0, 0, BOTTOM | RIGHT },
				{ LEFT | BOTTOM, RIGHT, 0, 0, 0, BOTTOM, RIGHT },
				{ LEFT | BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM,
						BOTTOM | RIGHT, BOTTOM, BOTTOM | RIGHT } },
				new int[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0 } }, 0, 2));

		designList.add(new MapDesign("5", 7, 7, new int[][] {
				{ LEFT | TOP | RIGHT, TOP, TOP | BOTTOM, TOP | RIGHT, TOP, TOP,
						TOP | RIGHT | BOTTOM },
				{ LEFT, 0, 0, 0, 0, 0, RIGHT },
				{ LEFT, BOTTOM, RIGHT, 0, 0, RIGHT, RIGHT },
				{ LEFT, 0, 0, RIGHT | BOTTOM, RIGHT, 0, RIGHT | BOTTOM },
				{ LEFT | BOTTOM, 0, 0, 0, BOTTOM, 0, RIGHT },
				{ LEFT, RIGHT, RIGHT | BOTTOM, 0, 0, 0, RIGHT },
				{ LEFT | BOTTOM, BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM,
						BOTTOM | RIGHT, BOTTOM | RIGHT } }, new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 } }, 4, 1));

		designList.add(new MapDesign("6", 7, 7, new int[][] {
				{ LEFT | TOP | RIGHT, TOP | BOTTOM, TOP | RIGHT, TOP, TOP,
						TOP | RIGHT, TOP | RIGHT },
				{ LEFT | BOTTOM, 0, 0, 0, RIGHT | BOTTOM, 0, RIGHT },
				{ LEFT, BOTTOM, 0, 0, 0, BOTTOM, RIGHT },
				{ LEFT | BOTTOM, 0, RIGHT, RIGHT, 0, 0, RIGHT },
				{ LEFT, RIGHT, BOTTOM, 0, 0, 0, BOTTOM | RIGHT },
				{ LEFT, 0, 0, BOTTOM | RIGHT, BOTTOM, 0, RIGHT },
				{ LEFT | BOTTOM | RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM,
						BOTTOM | RIGHT, BOTTOM | RIGHT } }, new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 1 } }, 0, 0));

		designList
				.add(new MapDesign("7", 8, 8,
						new int[][] {
								{ LEFT | TOP, TOP | RIGHT, TOP | BOTTOM, TOP,
										TOP, TOP | BOTTOM, TOP | RIGHT,
										TOP | RIGHT },
								{ LEFT | BOTTOM, 0, 0, RIGHT | BOTTOM, 0, 0, 0,
										RIGHT | BOTTOM },
								{ LEFT | BOTTOM, 0, 0, 0, RIGHT, RIGHT, BOTTOM,
										RIGHT },
								{ LEFT, 0, RIGHT, 0, 0, 0, 0, RIGHT },
								{ LEFT, BOTTOM, 0, 0, 0, 0, 0,
										LEFT | BOTTOM | RIGHT },
								{ LEFT, 0, BOTTOM, 0, BOTTOM | RIGHT, 0, 0,
										RIGHT },
								{ LEFT | RIGHT, 0, 0, 0, BOTTOM, 0, 0,
										BOTTOM | RIGHT },
								{ LEFT | BOTTOM, BOTTOM | RIGHT, BOTTOM,
										BOTTOM, BOTTOM | RIGHT, BOTTOM, BOTTOM,
										BOTTOM | RIGHT } }, new int[][] {
								{ 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 1 },
								{ 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0 } }, 0, 2));

		designList.add(new MapDesign("8", 9, 9,
				new int[][] {
						{ LEFT | TOP | BOTTOM, TOP, TOP | RIGHT, TOP, TOP,
								TOP | BOTTOM, TOP | RIGHT, TOP,
								TOP | RIGHT | BOTTOM },
						{ LEFT, RIGHT | BOTTOM, 0, 0, BOTTOM, 0, 0, RIGHT,
								RIGHT },
						{ LEFT, 0, 0, 0, 0, RIGHT, RIGHT, 0, RIGHT },
						{ LEFT | BOTTOM, RIGHT, 0, BOTTOM, RIGHT, BOTTOM, 0, 0,
								RIGHT | BOTTOM },
						{ LEFT, 0, 0, BOTTOM, 0, BOTTOM, RIGHT, 0, RIGHT },
						{ LEFT, BOTTOM, 0, RIGHT, RIGHT, 0, BOTTOM, 0,
								BOTTOM | RIGHT },
						{ LEFT | RIGHT, 0, BOTTOM, 0, BOTTOM, 0, 0, 0, RIGHT },
						{ LEFT | BOTTOM, 0, 0, BOTTOM, RIGHT, 0, 0, BOTTOM,
								RIGHT },
						{ LEFT | BOTTOM, BOTTOM, BOTTOM, BOTTOM | RIGHT,
								BOTTOM, BOTTOM, BOTTOM, BOTTOM | RIGHT,
								BOTTOM | RIGHT } }, new int[][] {
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 1, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } }, 5, 3));

		designList
				.add(new MapDesign("9", 9, 9,
						new int[][] {
								{ LEFT | TOP | RIGHT, TOP, TOP | RIGHT,
										TOP | BOTTOM, TOP, TOP, TOP | RIGHT,
										TOP, TOP | RIGHT | BOTTOM },
								{ LEFT, BOTTOM, 0, BOTTOM, BOTTOM, RIGHT,
										BOTTOM, 0, RIGHT },
								{ LEFT | BOTTOM, RIGHT, 0, RIGHT, 0, 0, 0,
										RIGHT, RIGHT },
								{ LEFT, 0, BOTTOM, 0, 0, BOTTOM | RIGHT, 0,
										BOTTOM, RIGHT },
								{ LEFT | BOTTOM, 0, RIGHT, RIGHT, BOTTOM, 0, 0,
										0, RIGHT },
								{ LEFT, 0, 0, RIGHT, 0, BOTTOM, 0, RIGHT,
										BOTTOM | RIGHT },
								{ LEFT | RIGHT, RIGHT, 0, BOTTOM, 0, 0, 0, 0,
										RIGHT },
								{ LEFT, BOTTOM, 0, 0, RIGHT, 0, RIGHT, BOTTOM,
										RIGHT },
								{ LEFT | BOTTOM, BOTTOM | RIGHT, BOTTOM,
										BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM,
										BOTTOM | RIGHT, BOTTOM | RIGHT } },
						new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 1, 0 } }, 1, 8));

		designList.add(new MapDesign("10", 10, 10, new int[][] {
				{ LEFT | TOP | RIGHT, TOP, TOP, TOP, TOP, TOP | BOTTOM,
						TOP | RIGHT, TOP, TOP | RIGHT, TOP | RIGHT },
				{ LEFT, RIGHT, 0, 0, 0, 0, 0, 0, 0, BOTTOM | RIGHT },
				{ LEFT | BOTTOM, 0, 0, BOTTOM | RIGHT, 0, 0, BOTTOM | RIGHT,
						BOTTOM, 0, RIGHT },
				{ LEFT, 0, BOTTOM | RIGHT, 0, 0, 0, RIGHT, 0, 0, RIGHT },
				{ LEFT | BOTTOM, 0, BOTTOM, RIGHT, 0, 0, BOTTOM, 0,
						BOTTOM | RIGHT, RIGHT },
				{ LEFT, BOTTOM, 0, 0, RIGHT, 0, 0, BOTTOM, RIGHT, RIGHT },
				{ LEFT, 0, BOTTOM | RIGHT, BOTTOM, 0, 0, BOTTOM | RIGHT, 0, 0,
						BOTTOM | RIGHT },
				{ LEFT | BOTTOM, 0, RIGHT, 0, 0, BOTTOM | RIGHT, 0, 0, 0,
						BOTTOM | RIGHT },
				{ LEFT, 0, BOTTOM, 0, 0, 0, RIGHT, BOTTOM, 0, RIGHT },
				{ LEFT | BOTTOM, BOTTOM | RIGHT, BOTTOM, BOTTOM,
						BOTTOM | RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM | RIGHT,
						BOTTOM | RIGHT } }, new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 } }, 0, 0));

		designList
				.add(new MapDesign(
						"11",
						10,
						10,
						new int[][] {
								{ LEFT | TOP | RIGHT, TOP | BOTTOM, TOP,
										TOP | RIGHT, TOP, TOP, TOP | RIGHT,
										TOP, TOP, TOP | RIGHT | BOTTOM },
								{ LEFT, RIGHT, 0, 0, 0, 0, BOTTOM, 0,
										BOTTOM | RIGHT, RIGHT },
								{ LEFT | RIGHT, 0, BOTTOM | RIGHT, 0, RIGHT, 0,
										0, 0, BOTTOM, RIGHT },
								{ LEFT, 0, RIGHT, 0, 0, 0, RIGHT, 0, 0,
										BOTTOM | RIGHT },
								{ LEFT | BOTTOM, 0, 0, 0, BOTTOM | RIGHT,
										BOTTOM, 0, 0, RIGHT, RIGHT },
								{ LEFT | RIGHT, RIGHT, 0, BOTTOM, RIGHT, 0, 0,
										0, BOTTOM, RIGHT },
								{ LEFT, BOTTOM, 0, 0, 0, 0, 0, BOTTOM, 0,
										BOTTOM | RIGHT },
								{ LEFT, BOTTOM, BOTTOM, RIGHT, RIGHT, BOTTOM,
										0, 0, 0, RIGHT },
								{ LEFT | BOTTOM | RIGHT, 0, 0, 0, 0, BOTTOM,
										RIGHT, RIGHT, BOTTOM, RIGHT },
								{ LEFT | BOTTOM, BOTTOM | RIGHT, BOTTOM,
										BOTTOM | RIGHT, BOTTOM, BOTTOM, BOTTOM,
										BOTTOM, BOTTOM | RIGHT, BOTTOM | RIGHT } },
						new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 } }, 0, 0));

		designList.add(new MapDesign("12", 10, 10,
				new int[][] {
						{ LEFT | TOP | RIGHT, TOP | BOTTOM, TOP, TOP,
								TOP | RIGHT, TOP, TOP, TOP | BOTTOM,
								TOP | RIGHT, TOP | RIGHT },
						{ LEFT, 0, RIGHT, 0, 0, 0, 0, 0, 0, RIGHT },
						{ LEFT, 0, 0, 0, BOTTOM, 0, BOTTOM | RIGHT, 0, BOTTOM,
								RIGHT },
						{ LEFT | RIGHT, 0, BOTTOM, 0, BOTTOM, 0, 0, 0, RIGHT,
								RIGHT },
						{ LEFT | BOTTOM, 0, 0, 0, RIGHT, RIGHT, 0, 0, BOTTOM,
								RIGHT },
						{ LEFT | RIGHT, RIGHT, 0, 0, 0, BOTTOM, 0, BOTTOM, 0,
								RIGHT },
						{ LEFT, 0, 0, BOTTOM, 0, BOTTOM, 0, 0, RIGHT, RIGHT },
						{ LEFT | RIGHT, 0, 0, RIGHT, BOTTOM, 0, 0, 0, 0,
								BOTTOM | RIGHT },
						{ LEFT | BOTTOM, 0, 0, 0, RIGHT, 0, BOTTOM, RIGHT,
								BOTTOM, RIGHT },
						{ LEFT | BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM,
								BOTTOM, BOTTOM | RIGHT, BOTTOM, BOTTOM,
								BOTTOM | RIGHT, BOTTOM | RIGHT } },
				new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 } }, 1, 0));

		designList.add(new MapDesign("13", 10, 10, new int[][] {
				{ LEFT | TOP | RIGHT, TOP | RIGHT, TOP, TOP, TOP | RIGHT, TOP,
						TOP | RIGHT, TOP | BOTTOM, TOP, TOP | RIGHT },
				{ LEFT | RIGHT, 0, BOTTOM, 0, 0, 0, 0, 0, 0, RIGHT },
				{ LEFT, 0, 0, RIGHT, RIGHT, 0, 0, RIGHT, 0, BOTTOM | RIGHT },
				{ LEFT, RIGHT, 0, 0, 0, 0, BOTTOM, 0, 0, RIGHT },
				{ LEFT | BOTTOM, 0, BOTTOM, 0, BOTTOM | RIGHT, BOTTOM, 0,
						BOTTOM | RIGHT, 0, BOTTOM | RIGHT },
				{ LEFT, 0, BOTTOM, 0, RIGHT, 0, 0, BOTTOM, 0, RIGHT },
				{ LEFT | BOTTOM, 0, BOTTOM, 0, 0, RIGHT, BOTTOM, 0, RIGHT,
						RIGHT },
				{ LEFT, RIGHT, 0, BOTTOM, RIGHT, RIGHT, 0, 0, RIGHT, RIGHT },
				{ LEFT | RIGHT, 0, 0, 0, 0, 0, 0, RIGHT, 0, RIGHT },
				{ LEFT | BOTTOM, BOTTOM, BOTTOM, BOTTOM | RIGHT,
						BOTTOM | RIGHT, BOTTOM | RIGHT, BOTTOM, BOTTOM,
						BOTTOM | RIGHT, BOTTOM | RIGHT } }, new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 } }, 0, 0));

		designList
				.add(new MapDesign(
						"14",
						10,
						10,
						new int[][] {
								{ LEFT | TOP, TOP | RIGHT, TOP, TOP, TOP,
										TOP | RIGHT, TOP, TOP | BOTTOM,
										TOP | RIGHT, TOP | RIGHT },
								{ LEFT, BOTTOM | RIGHT, BOTTOM, 0, BOTTOM, 0,
										RIGHT, 0, 0, RIGHT },
								{ LEFT | RIGHT, 0, 0, 0, 0, BOTTOM, 0, RIGHT,
										0, RIGHT },
								{ LEFT, 0, BOTTOM, RIGHT, 0, 0, BOTTOM, 0, 0,
										RIGHT },
								{ LEFT | BOTTOM, 0, 0, 0, BOTTOM, RIGHT, 0, 0,
										BOTTOM, RIGHT },
								{ LEFT, 0, RIGHT, BOTTOM, RIGHT, 0, RIGHT, 0,
										0, BOTTOM | RIGHT },
								{ LEFT, BOTTOM | RIGHT, 0, BOTTOM, 0, 0,
										BOTTOM, 0, 0, RIGHT },
								{ LEFT | BOTTOM, 0, 0, RIGHT, 0, 0, 0, 0,
										BOTTOM | RIGHT, RIGHT },
								{ LEFT, 0, 0, BOTTOM, 0, BOTTOM, 0, RIGHT, 0,
										RIGHT },
								{ LEFT | BOTTOM | RIGHT, BOTTOM, BOTTOM,
										BOTTOM, BOTTOM, BOTTOM, BOTTOM | RIGHT,
										BOTTOM | RIGHT, BOTTOM, BOTTOM | RIGHT } },
						new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }, 1, 1));

		designList.add(new MapDesign("15", 5, 5, new int[][] {
				{ LEFT | TOP | BOTTOM, TOP | RIGHT, TOP, TOP | BOTTOM,
						TOP | RIGHT },
				{ LEFT, 0, 0, RIGHT, RIGHT },
				{ LEFT | RIGHT, RIGHT, BOTTOM, 0, RIGHT },
				{ LEFT, 0, 0, 0, RIGHT | BOTTOM },
				{ LEFT | BOTTOM | RIGHT, BOTTOM, RIGHT | BOTTOM, BOTTOM,
						RIGHT | BOTTOM } }, new int[][] { { 1, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 1 } }, 2, 3));

		designList
				.add(new MapDesign("16", 5, 5,
						new int[][] {
								{ LEFT | TOP | BOTTOM, TOP, TOP | BOTTOM,
										TOP | RIGHT, TOP | RIGHT },
								{ LEFT, 0, TOP | RIGHT, 0, RIGHT },
								{ LEFT | RIGHT, 0, BOTTOM, 0, BOTTOM | RIGHT },
								{ LEFT | BOTTOM, RIGHT, 0, BOTTOM,
										RIGHT | BOTTOM },
								{ LEFT | BOTTOM, BOTTOM, BOTTOM, BOTTOM,
										RIGHT | BOTTOM } }, new int[][] {
								{ 0, 1, 0, 1, 0 }, { 1, 0, 0, 0, 1 },
								{ 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1 },
								{ 0, 1, 0, 1, 0 } }, 2, 2));

		designList
				.add(new MapDesign("17", 5, 5, new int[][] {
						{ LEFT | TOP | BOTTOM, TOP | RIGHT, TOP, TOP | BOTTOM,
								TOP | RIGHT | BOTTOM },
						{ LEFT | RIGHT, 0, 0, BOTTOM, RIGHT },
						{ LEFT, BOTTOM, 0, 0, RIGHT | BOTTOM },
						{ LEFT, RIGHT, RIGHT, BOTTOM, RIGHT },
						{ LEFT | BOTTOM | RIGHT, BOTTOM, BOTTOM, BOTTOM,
								RIGHT | BOTTOM } },
						new int[][] { { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 },
								{ 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0 },
								{ 1, 0, 0, 0, 1 } }, 2, 2));

		designList.add(new MapDesign("18", 6, 6,
				new int[][] {
						{ LEFT | TOP | RIGHT, TOP, TOP | RIGHT, TOP, TOP,
								TOP | RIGHT },
						{ LEFT | BOTTOM, 0, BOTTOM, RIGHT, 0, BOTTOM | RIGHT },
						{ LEFT | RIGHT, 0, 0, 0, 0, RIGHT },
						{ LEFT | BOTTOM, 0, RIGHT, BOTTOM, RIGHT,
								BOTTOM | RIGHT },
						{ LEFT, 0, BOTTOM, 0, 0, RIGHT },
						{ LEFT | BOTTOM | RIGHT, BOTTOM, BOTTOM,
								BOTTOM | RIGHT, BOTTOM, BOTTOM | RIGHT } },
				new int[][] { { 0, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1 },
						{ 1, 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0 } }, 0, 2));

		designList.add(new MapDesign("19", 6, 6, new int[][] {
				{ LEFT | TOP | BOTTOM, TOP | RIGHT, TOP, TOP, TOP | BOTTOM,
						TOP | RIGHT },
				{ LEFT, 0, BOTTOM | RIGHT, 0, RIGHT, RIGHT },
				{ LEFT | BOTTOM | RIGHT, RIGHT, 0, 0, 0, BOTTOM | RIGHT },
				{ LEFT, 0, 0, BOTTOM, BOTTOM, RIGHT },
				{ LEFT | RIGHT, BOTTOM, 0, 0, BOTTOM, RIGHT },
				{ LEFT | BOTTOM, BOTTOM | RIGHT, BOTTOM, BOTTOM | RIGHT,
						BOTTOM, BOTTOM | RIGHT } }, new int[][] {
				{ 1, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0 },
				{ 0, 1, 0, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0 } }, 4, 1));

		designList.add(new MapDesign("20", 6, 6, new int[][] {
				{ LEFT | TOP | RIGHT, TOP, TOP | RIGHT, TOP, TOP,
						TOP | RIGHT | BOTTOM },
				{ LEFT, BOTTOM, 0, BOTTOM | RIGHT, RIGHT, RIGHT },
				{ LEFT | BOTTOM, RIGHT, 0, 0, 0, BOTTOM | RIGHT },
				{ LEFT, 0, BOTTOM, RIGHT, 0, RIGHT },
				{ LEFT | BOTTOM | RIGHT, RIGHT, BOTTOM, 0, BOTTOM | RIGHT,
						RIGHT },
				{ LEFT | BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM, BOTTOM,
						BOTTOM | RIGHT } }, new int[][] { { 1, 0, 1, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0 }, { 1, 0, 1, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 1 } }, 1, 1));

		designList.add(new MapDesign("21", 7, 7, new int[][] {
				{ LEFT | TOP, TOP | RIGHT, TOP, TOP | RIGHT, TOP, TOP | BOTTOM,
						TOP | RIGHT },
				{ LEFT | BOTTOM, 0, 0, BOTTOM, 0, 0, RIGHT },
				{ LEFT, BOTTOM, 0, RIGHT, 0, 0, BOTTOM | RIGHT },
				{ LEFT, 0, BOTTOM | RIGHT, 0, BOTTOM, 0, RIGHT | BOTTOM },
				{ LEFT | BOTTOM, 0, 0, 0, RIGHT, 0, RIGHT },
				{ LEFT | RIGHT, 0, 0, 0, 0, BOTTOM, RIGHT },
				{ LEFT | BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM,
						BOTTOM | RIGHT, BOTTOM, BOTTOM | RIGHT } },
				new int[][] { { 0, 1, 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 1, 0, 0, 1 },
						{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 1, 0, 1, 0, 1, 0 } }, 6, 4));

		designList.add(new MapDesign("22", 7, 7, new int[][] {
				{ LEFT | TOP, TOP | RIGHT, TOP | BOTTOM, TOP, TOP | RIGHT, TOP,
						TOP | RIGHT },
				{ LEFT | RIGHT, 0, 0, 0, 0, RIGHT, RIGHT },
				{ LEFT | BOTTOM, 0, BOTTOM | RIGHT, RIGHT, 0, BOTTOM, RIGHT },
				{ LEFT, 0, 0, RIGHT, BOTTOM, 0, RIGHT | BOTTOM },
				{ LEFT | RIGHT, RIGHT, 0, BOTTOM, 0, 0, RIGHT | BOTTOM },
				{ LEFT, BOTTOM, 0, 0, RIGHT, 0, RIGHT },
				{ LEFT | BOTTOM | RIGHT, BOTTOM, BOTTOM | RIGHT, BOTTOM,
						BOTTOM, BOTTOM, BOTTOM | RIGHT } }, new int[][] {
				{ 1, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 1, 0, 0, 0, 0 } }, 3, 1));

		designList.add(new MapDesign("23", 8, 8, new int[][] {
				{ LEFT | TOP, TOP | BOTTOM, TOP | BOTTOM, TOP, TOP | BOTTOM,
						TOP | RIGHT, TOP, TOP | RIGHT },
				{ LEFT, 0, 0, RIGHT, 0, 0, BOTTOM, RIGHT },
				{ LEFT, RIGHT, 0, BOTTOM, 0, 0, RIGHT, RIGHT },
				{ LEFT, 0, BOTTOM, 0, RIGHT, BOTTOM, 0, RIGHT },
				{ LEFT, RIGHT, 0, 0, BOTTOM, 0, 0, RIGHT | BOTTOM },
				{ LEFT | RIGHT | BOTTOM, 0, 0, BOTTOM, 0, 0, BOTTOM, RIGHT },
				{ LEFT, 0, 0, 0, 0, BOTTOM | RIGHT, 0, RIGHT },
				{ LEFT | BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM, BOTTOM,
						BOTTOM, BOTTOM | RIGHT, BOTTOM | RIGHT } },
				new int[][] { { 0, 0, 0, 1, 0, 0, 1, 0 },
						{ 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0 },
						{ 0, 0, 0, 1, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1 },
						{ 1, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0 },
						{ 0, 1, 0, 1, 0, 0, 0, 1 } }, 4, 5));

		designList
				.add(new MapDesign("24", 8, 8,
						new int[][] {
								{ LEFT | TOP, TOP | BOTTOM, TOP, TOP,
										TOP | RIGHT, TOP | BOTTOM, TOP,
										TOP | RIGHT },
								{ LEFT, 0, BOTTOM | RIGHT, 0, 0, 0, BOTTOM,
										RIGHT },
								{ LEFT | BOTTOM, 0, 0, 0, RIGHT, 0, 0, RIGHT },
								{ LEFT, 0, RIGHT, BOTTOM, 0, 0, 0, RIGHT },
								{ LEFT, 0, 0, 0, 0, RIGHT, RIGHT,
										RIGHT | BOTTOM },
								{ LEFT, RIGHT, BOTTOM, 0, 0, 0, 0, RIGHT },
								{ LEFT | RIGHT, BOTTOM, 0, 0, 0, BOTTOM, RIGHT,
										RIGHT },
								{ LEFT | BOTTOM, BOTTOM, BOTTOM,
										BOTTOM | RIGHT, BOTTOM, BOTTOM, BOTTOM,
										BOTTOM | RIGHT } }, new int[][] {
								{ 0, 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 1, 0, 0, 0, 0, 0, 1 },
								{ 0, 0, 0, 0, 0, 0, 1, 0 },
								{ 1, 0, 0, 1, 0, 0, 0, 1 },
								{ 0, 0, 0, 0, 0, 1, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 1, 0, 1, 0, 0, 0 },
								{ 1, 0, 0, 0, 0, 0, 1, 0 } }, 3, 5));

		designList
				.add(new MapDesign(
						"25",
						8,
						8,
						new int[][] {
								{ LEFT | TOP, TOP | BOTTOM, TOP | RIGHT, TOP,
										TOP, TOP, TOP | RIGHT | BOTTOM,
										TOP | RIGHT },
								{ LEFT, 0, 0, 0, RIGHT, 0, 0, RIGHT },
								{ LEFT, 0, BOTTOM, 0, 0, 0, RIGHT | BOTTOM,
										RIGHT },
								{ LEFT | BOTTOM | RIGHT, RIGHT, 0, 0, 0, 0, 0,
										RIGHT | BOTTOM },
								{ LEFT, 0, 0, BOTTOM | RIGHT, 0, RIGHT, 0,
										RIGHT },
								{ LEFT, 0, 0, 0, BOTTOM, 0, 0, BOTTOM | RIGHT },
								{ LEFT, 0, BOTTOM, 0, BOTTOM, 0, 0, RIGHT },
								{ LEFT | BOTTOM | RIGHT, BOTTOM, BOTTOM,
										BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM,
										BOTTOM | RIGHT } }, new int[][] {
								{ 0, 1, 0, 0, 0, 1, 0, 1 },
								{ 0, 0, 0, 1, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 1, 0 },
								{ 1, 0, 1, 0, 1, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 1 },
								{ 0, 1, 0, 1, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 1, 0, 0, 0 },
								{ 1, 0, 1, 0, 0, 0, 1, 0 } }, 4, 4));

		designList
				.add(new MapDesign("26", 10, 10,
						new int[][] {
								{ LEFT | TOP, TOP, TOP, TOP, TOP | BOTTOM,
										TOP | RIGHT, TOP, TOP | RIGHT, TOP,
										TOP | RIGHT },
								{ LEFT | BOTTOM, RIGHT, BOTTOM, 0, 0, 0, 0, 0,
										BOTTOM, RIGHT },
								{ LEFT | RIGHT, 0, 0, 0, BOTTOM | RIGHT, 0, 0,
										BOTTOM, 0, RIGHT },
								{ LEFT, 0, 0, BOTTOM, 0, BOTTOM, 0, RIGHT, 0,
										BOTTOM | RIGHT },
								{ LEFT, BOTTOM | RIGHT, 0, 0, 0, 0, RIGHT, 0,
										0, RIGHT },
								{ LEFT, 0, BOTTOM, 0, RIGHT, 0, BOTTOM, 0, 0,
										RIGHT },
								{ LEFT | RIGHT, 0, 0, 0, 0, 0, 0, RIGHT, 0,
										BOTTOM | RIGHT },
								{ LEFT, BOTTOM, 0, RIGHT, BOTTOM, 0, 0, 0, 0,
										RIGHT },
								{ LEFT | BOTTOM, RIGHT, 0, BOTTOM, 0, RIGHT, 0,
										RIGHT, BOTTOM, RIGHT },
								{ LEFT | BOTTOM, BOTTOM, BOTTOM,
										RIGHT | BOTTOM, BOTTOM, BOTTOM,
										BOTTOM | RIGHT, BOTTOM, BOTTOM,
										BOTTOM | RIGHT } }, new int[][] {
								{ 1, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
								{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
								{ 1, 0, 0, 1, 0, 1, 0, 1, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
								{ 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 },
								{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
								{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 1, 0, 1, 0 },
								{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 } }, 5, 6));

		designList.add(new MapDesign("27", 10, 10, new int[][] {
				{ LEFT | TOP, TOP | RIGHT, TOP, TOP | RIGHT, TOP, TOP | BOTTOM,
						TOP, TOP | RIGHT, TOP, TOP | RIGHT },
				{ LEFT, BOTTOM, RIGHT, 0, 0, BOTTOM, 0, BOTTOM, 0, RIGHT },
				{ LEFT | BOTTOM, RIGHT, 0, 0, 0, RIGHT, 0, 0, BOTTOM | RIGHT,
						RIGHT },
				{ LEFT, 0, BOTTOM | RIGHT, 0, 0, 0, RIGHT, 0, 0, RIGHT },
				{ LEFT | RIGHT, 0, 0, 0, BOTTOM | RIGHT, 0, 0, BOTTOM, RIGHT,
						RIGHT },
				{ LEFT, RIGHT, RIGHT, BOTTOM, 0, RIGHT, 0, BOTTOM, 0,
						BOTTOM | RIGHT },
				{ LEFT | BOTTOM, 0, 0, 0, 0, BOTTOM, 0, RIGHT, RIGHT, RIGHT },
				{ LEFT, 0, RIGHT, 0, 0, 0, 0, 0, BOTTOM, RIGHT },
				{ LEFT, BOTTOM, 0, 0, BOTTOM | RIGHT, RIGHT, BOTTOM, 0, RIGHT,
						RIGHT },
				{ LEFT | BOTTOM, BOTTOM, BOTTOM, BOTTOM | RIGHT, BOTTOM,
						BOTTOM, BOTTOM, BOTTOM, BOTTOM, BOTTOM | RIGHT } },
				new int[][] { { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
						{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 1, 0, 0, 1, 0, 0, 1, 0 },
						{ 1, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
						{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
						{ 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
						{ 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
						{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
						{ 0, 1, 0, 0, 1, 0, 0, 0, 1, 0 },
						{ 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 } }, 5, 4));

	}

	private MapDesigns() {
		throw new AssertionError();
	}
}
