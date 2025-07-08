INSERT INTO farm (name, location) VALUES ('Green Acres', 'Springfield');
INSERT INTO crop (name, type, description) VALUES ('Corn', 'Grain', 'A tall annual grass.');
INSERT INTO season (name, season_year) VALUES ('Spring', 2025);
INSERT INTO planted (farm_id, crop_id, season_id, planting_area, expected_yield, planted_date) VALUES (1, 1, 1, 50.0, 1000.0, '2025-04-15');
INSERT INTO harvested (farm_id, crop_id, season_id, actual_yield, date_harvested) VALUES (1, 1, 1, 950.0, '2025-09-10');