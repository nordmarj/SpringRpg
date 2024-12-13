DELETE FROM location_links;
DELETE FROM users;
DELETE FROM actors;
DELETE FROM locations;

INSERT INTO users (name, email) VALUES
  ('Jens', 'nordmarj@gmail.com'),
  ('user2', 'user2@lolo.com');
  
INSERT INTO locations (name, description) VALUES
  ('Lobby', 'This is the lobby.'),
  ('Restaurant', 'This is the restaurant.'),
  ('Spa', 'This is the spa.');

INSERT INTO actors (name) VALUES
  ('Player1'),
  ('Player2');

UPDATE users
SET actor_id = (
    SELECT id 
    FROM actors 
    WHERE name = 'Player1'
)
WHERE name = 'Jens';

UPDATE users
SET actor_id = (
    SELECT id 
    FROM actors 
    WHERE name = 'Player2'
)
WHERE name = 'user2';

UPDATE actors
SET location_id = (
    SELECT id 
    FROM locations 
    WHERE name = 'Lobby'
);

INSERT INTO location_links (source_location_id, destination_location_id)
SELECT 
    (SELECT id FROM locations WHERE name = 'Lobby'),
    (SELECT id FROM locations WHERE name = 'Restaurant')
UNION ALL
SELECT 
    (SELECT id FROM locations WHERE name = 'Restaurant'),
    (SELECT id FROM locations WHERE name = 'Lobby');

INSERT INTO location_links (source_location_id, destination_location_id)
SELECT 
    (SELECT id FROM locations WHERE name = 'Lobby'),
    (SELECT id FROM locations WHERE name = 'Spa')
UNION ALL
SELECT 
    (SELECT id FROM locations WHERE name = 'Spa'),
    (SELECT id FROM locations WHERE name = 'Lobby');


--DROP TABLE location_links
--DROP TABLE users
--DROP TABLE actors
--DROP TABLE locations
