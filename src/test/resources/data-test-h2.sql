-- noinspection SpellCheckingInspectionForFile

insert into PUBLIC.USERS (USERNAME, PASSWORD, ENABLED)
values ('Gargoyle', '$2a$10$XxoeY6KLKqmxTuqdDXDD2ufWYKZD6y9hwRIOxoEQNa05JdORZzfEu', true);
insert into PUBLIC.AUTHORITIES (USERNAME, AUTHORITY)
values ('Gargoyle', 'ROLE_ADMIN');
insert into PUBLIC.AUTHORITIES (USERNAME, AUTHORITY)
values ('Gargoyle', 'ROLE_USER');

insert into PUBLIC.BANDS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, LETTER, GENRE, IMAGE_TYPE, IMAGE_CONTENT_LENGTH,
                          IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH,
                          THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, THUMBNAIL_PATH)
values (1, '81eda4e7-fa4d-4f26-9ef1-55b8ac66f50c', 'A Band', 'A Band',
        'A Banddescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Banddescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        'A', null, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null);
insert into PUBLIC.BANDS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, LETTER, GENRE, IMAGE_TYPE, IMAGE_CONTENT_LENGTH,
                          IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH,
                          THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, THUMBNAIL_PATH)
values (2, '0f626ea6-83df-43a0-b2b6-e7c09766824a', 'B Band', 'B Band',
        'B Banddescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Banddescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        'B', null, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null);
insert into PUBLIC.BANDS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, LETTER, GENRE, IMAGE_TYPE, IMAGE_CONTENT_LENGTH,
                          IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH,
                          THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, THUMBNAIL_PATH)
values (3, 'f47be83b-85f8-4773-8587-8b951cf37def', 'C Band', 'C Band',
        'C Banddescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Banddescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        'C', null, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null);
insert into PUBLIC.ALBUMS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH,
                           IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH,
                           THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID)
values (1, '40106238-72c7-40fc-80e7-5874736fb71b', 'A Album - A Band', 'A Album - A Band',
        'A Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        0, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null, 1);
insert into PUBLIC.ALBUMS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH,
                           IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH,
                           THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID)
values (2, '55be802c-d278-416a-b935-6fc5072fc8de', 'B Album - A Band', 'B Album - A Band',
        'B Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        0, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null, 1);
insert into PUBLIC.ALBUMS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH,
                           IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH,
                           THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID)
values (3, '6d4c68c6-2afc-4db9-9922-2c00a4d9126f', 'C Album - A Band', 'C Album - A Band',
        'C Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        0, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null, 1);
insert into PUBLIC.ALBUMS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH,
                           IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH,
                           THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID)
values (4, '6851256a-4c9a-49ff-88f6-34b4b8d5731d', 'A Album - B Band', 'A Album - B Band',
        'A Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        0, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null, 2);
insert into PUBLIC.ALBUMS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH,
                           IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH,
                           THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID)
values (5, '3575cd56-9c61-4510-8a6a-a529fbd22cd7', 'B Album - B Band', 'B Album - B Band',
        'B Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        0, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null, 2);
insert into PUBLIC.ALBUMS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH,
                           IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH,
                           THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID)
values (6, '62dd2757-f189-42c7-a45f-cb6a34acc7fd', 'C Album - B Band', 'C Album - B Band',
        'C Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        0, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null, 2);
insert into PUBLIC.ALBUMS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH,
                           IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH,
                           THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID)
values (7, 'c2162388-3d4d-467e-a6e7-cbed73812697', 'A Album - C Band', 'A Album - C Band',
        'A Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        0, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null, 3);
insert into PUBLIC.ALBUMS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH,
                           IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH,
                           THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID)
values (8, '55c9f198-c051-4c7b-9566-4b11814929aa', 'B Album - C Band', 'B Album - C Band',
        'B Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        0, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null, 3);
insert into PUBLIC.ALBUMS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, YEAR, IMAGE_TYPE, IMAGE_CONTENT_LENGTH, IMAGE_WIDTH,
                           IMAGE_HEIGHT, IMAGE_PATH, THUMBNAIL_TYPE, THUMBNAIL_CONTENT_LENGTH, THUMBNAIL_WIDTH,
                           THUMBNAIL_HEIGHT, THUMBNAIL_PATH, BAND_ID)
values (9, '05a92afd-59f8-4a89-af2e-406126d54039', 'C Album - C Band', 'C Album - C Band',
        'C Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Albumdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        0, 'application/octet-stream', 0, 0, 0, null, 'application/octet-stream', '0', 0, 0, null, 3);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (1, '103bd9de-c944-496f-88b0-d92d89158336', 'A Track - A Album - A Band', 'A Track - A Album - A Band',
        'A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        1, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 1);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (2, '17f34586-5f51-4271-9dfd-eaf484f8e069', 'B Track - A Album - A Band', 'B Track - A Album - A Band',
        'B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        2, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 1);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (3, 'cec71100-6bb6-445b-a755-d1e4b342cb2a', 'C Track - A Album - A Band', 'C Track - A Album - A Band',
        'C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        3, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 1);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (4, '38b9cd41-7a8c-4af7-9866-7396be7afb3e', 'A Track - B Album - A Band', 'A Track - B Album - A Band',
        'A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        1, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 2);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (5, 'f53bdecb-fa4e-410d-9347-0a50b28d18b1', 'B Track - B Album - A Band', 'B Track - B Album - A Band',
        'B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        2, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 2);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (6, '59667c8c-a21d-4c94-9007-e03368e62f58', 'C Track - B Album - A Band', 'C Track - B Album - A Band',
        'C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        3, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 2);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (7, '08c8a82f-bf93-4c4b-869f-c0065f5c3163', 'A Track - C Album - A Band', 'A Track - C Album - A Band',
        'A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        1, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 3);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (8, '45ddf484-0de8-472b-b208-73ccaa8efed6', 'B Track - C Album - A Band', 'B Track - C Album - A Band',
        'B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        2, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 3);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (9, '8af817dc-da55-4795-ba13-fb615e9ba832', 'C Track - C Album - A Band', 'C Track - C Album - A Band',
        'C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        3, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 3);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (10, 'ca7e992e-db69-4e14-8d20-0b2ab3638597', 'A Track - A Album - B Band', 'A Track - A Album - B Band',
        'A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        1, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 4);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (11, '39861a77-0788-472a-8e2e-93d5fc99f135', 'B Track - A Album - B Band', 'B Track - A Album - B Band',
        'B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        2, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 4);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (12, 'e96a533a-1135-49cd-9c87-641b32b7ce50', 'C Track - A Album - B Band', 'C Track - A Album - B Band',
        'C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        3, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 4);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (13, '1d1fdd45-07fb-4dbc-acb6-4588a43240e0', 'A Track - B Album - B Band', 'A Track - B Album - B Band',
        'A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        1, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 5);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (14, 'c2cbfb6b-18dc-4aef-b1b1-cb0efb2b4dd6', 'B Track - B Album - B Band', 'B Track - B Album - B Band',
        'B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        2, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 5);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (15, 'dcfd665e-df68-4c41-88c9-64ce7ce099ff', 'C Track - B Album - B Band', 'C Track - B Album - B Band',
        'C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        3, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 5);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (16, 'af8a0956-419e-4972-ae80-1a22eaa1e97a', 'A Track - C Album - B Band', 'A Track - C Album - B Band',
        'A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        1, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 6);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (17, '1b68d103-d43c-46a2-9701-01fbea5025c3', 'B Track - C Album - B Band', 'B Track - C Album - B Band',
        'B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        2, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 6);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (18, 'f2e2b4d2-1c0d-4a75-886d-cbcb6e839860', 'C Track - C Album - B Band', 'C Track - C Album - B Band',
        'C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        3, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 6);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (19, '059a0607-1928-4c2c-b238-6fa887be2463', 'A Track - A Album - C Band', 'A Track - A Album - C Band',
        'A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        1, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 7);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (20, 'b6d68415-e41c-4fe2-bf19-becd805d68ee', 'B Track - A Album - C Band', 'B Track - A Album - C Band',
        'B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        2, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 7);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (21, 'dd5f42fb-71c6-4660-85c9-4bec638e6d78', 'C Track - A Album - C Band', 'C Track - A Album - C Band',
        'C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        3, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 7);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (22, '1e40375e-cda0-4525-aefc-9d752647cf50', 'A Track - B Album - C Band', 'A Track - B Album - C Band',
        'A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        1, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 8);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (23, '12b80252-bc42-4217-92f8-887e0587cf14', 'B Track - B Album - C Band', 'B Track - B Album - C Band',
        'B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        2, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 8);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (24, 'cca8e057-9b73-4076-8abf-269d4376249b', 'C Track - B Album - C Band', 'C Track - B Album - C Band',
        'C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        3, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 8);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (25, 'f44cfcaf-bcb6-49f6-b4a4-dde5131dccce', 'A Track - C Album - C Band', 'A Track - C Album - C Band',
        'A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>A Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        1, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 9);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (26, 'ebc0a8de-aefb-4ca6-90a8-4b182008220c', 'B Track - C Album - C Band', 'B Track - C Album - C Band',
        'B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>B Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        2, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 9);
insert into PUBLIC.TRACKS (ID, UID, ALIAS, TITLE, SOURCE, CONTENT, TRACK_NUMBER, AUDIO_TYPE, AUDIO_CONTENT_LENGTH,
                           AUDIO_LENGTH, AUDIO_BITRATE, AUDIO_PATH, VIDEO_TYPE, VIDEO_CONTENT_LENGTH, VIDEO_LENGTH,
                           VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_PATH, ALBUM_ID)
values (27, 'c2a523d9-a2ac-4191-8c0c-2c19d3a2b8e2', 'C Track - C Album - C Band', 'C Track - C Album - C Band',
        'C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description',
        '<p>C Trackdescription description description description description description description description description description description description description description description description description description description description description description description description description</p> ',
        3, 'application/octet-stream', '0', '0', 0, null, 'application/octet-stream', 0, null, 0, 0, null, 9);
