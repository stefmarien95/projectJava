import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MijnSongsComponent } from './mijn-songs.component';

describe('MijnSongsComponent', () => {
  let component: MijnSongsComponent;
  let fixture: ComponentFixture<MijnSongsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MijnSongsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MijnSongsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
