import Constants from 'expo-constants';

const BASE_URL = Constants.expoConfig.extra.API_BASE_URL;

export async function fetchAnnouncements(locationId) {
  const res = await fetch(`${BASE_URL}/announcements?locationId=${locationId}`);
  if (!res.ok) throw new Error('Failed to fetch announcements');
  return await res.json();
}